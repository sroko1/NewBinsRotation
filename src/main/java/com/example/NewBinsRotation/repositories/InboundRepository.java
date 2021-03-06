package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InboundRepository extends JpaRepository<Inbound, Integer> {
    void deleteById(int id);

    Inbound findById(int id);

    void save(int id);

}
