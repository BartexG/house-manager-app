package com.example.housemanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @GetMapping("/")
    public ModelAndView gethomePage() {
        ModelAndView homePage = new ModelAndView("mainpage.html");
        return homePage;
    }

    @GetMapping("/search")
    public ModelAndView getSearchPage() {
        ModelAndView searchPage = new ModelAndView("search.html");
        return searchPage;
    }

    @GetMapping("/id-search")
    public ModelAndView getIDSearchPage() {
        ModelAndView idSearchPage = new ModelAndView("idsearch.html");
        return idSearchPage;
    }

    @GetMapping("/admin")
    public ModelAndView getAdminPage() {
        ModelAndView adminPage = new ModelAndView("admin.html");
        return adminPage;
    }

    @GetMapping("/admin-rent")
    public ModelAndView getAdminRentPage() {
        ModelAndView adminPage = new ModelAndView("admin_rent.html");
        return adminPage;
    }

    @GetMapping("/check-rent")
    public ModelAndView getCreatePage() {
        ModelAndView modelAndView = new ModelAndView("check_rent.html");
        return modelAndView;
    }

    @GetMapping("/pay-rent")
    public ModelAndView getPayPage() {
        ModelAndView modelAndView = new ModelAndView("pay_rent.html");
        return modelAndView;
    }
}
