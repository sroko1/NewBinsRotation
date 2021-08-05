package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InboundRepository extends JpaRepository<Inbound, Integer> {
    void deleteById(Integer id);

    Optional<Inbound> findById(Integer id);

    void save(Integer id);
}
