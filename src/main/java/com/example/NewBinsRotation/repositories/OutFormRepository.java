package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.OutForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutFormRepository extends JpaRepository<OutForm, Integer> {

    void deleteById(int id);
    OutForm findById(int id);
    void save(int id);
}
