package com.example.housemanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}user1").roles("GUEST");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin1").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dweller").password("{noop}dweller1").roles("DWELLER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().formLogin();
        http.csrf().disable();
        //antMatchers("/admin").hasRole("USER");
        //.loginPage("/login.html").permitAll()
    }
}
