package com.example.housemanager.model.repositories;

import com.example.housemanager.model.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatsRepository extends JpaRepository<Flat, Long> {
}
