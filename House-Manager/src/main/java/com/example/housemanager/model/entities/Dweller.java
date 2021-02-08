package com.example.housemanager.model.entities;


import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Dweller {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;

    private String email;

    @ManyToOne
    @JoinColumn(name = "dweller")
    Flat flat;
}
