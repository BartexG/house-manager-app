package com.example.housemanager.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BlockOfFlats {

    @Id
    @GeneratedValue
    private Long id;

    private int blockNumber;

    @OneToMany
    @JoinColumn(name = "block_of_flats")
    private List<Flat> flats = new ArrayList<>();
}
