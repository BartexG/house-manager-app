package com.example.housemanager.model.repositories;

import com.example.housemanager.model.entities.Dweller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DwellerRepository extends JpaRepository<Dweller, Long> {

}
