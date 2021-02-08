package com.example.housemanager.model.entities;


import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Flat {

    @Id
    @GeneratedValue
    private Long id;

    private int number;

    //free, rented
    private String status;
    private boolean payedForMonth;

    @OneToMany
    @JoinColumn(name = "flat")
    private List<Dweller> dwellers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "block_of_flats")
    BlockOfFlats blockOfFlats;

}
