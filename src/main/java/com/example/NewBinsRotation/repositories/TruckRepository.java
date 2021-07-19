package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.Outbound;
import com.example.NewBinsRotation.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TruckRepository extends JpaRepository<Truck, Integer> {
    void deleteById(Integer id);
    Optional<Truck> findById(Integer id);
}

