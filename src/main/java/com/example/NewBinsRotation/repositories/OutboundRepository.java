package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutboundRepository extends JpaRepository<Outbound, Integer> {
    void deleteById(int id);
   Outbound findById(int id);
}

