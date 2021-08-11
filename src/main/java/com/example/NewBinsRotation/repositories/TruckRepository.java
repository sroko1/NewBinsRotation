package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.Outbound;
import com.example.NewBinsRotation.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TruckRepository extends JpaRepository<Truck, Integer> {
    void deleteById(int id);

   Truck findById(int id);

    void save(int id);
}

