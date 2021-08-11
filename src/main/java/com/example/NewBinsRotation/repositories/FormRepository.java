package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Integer> {

    void deleteById(int id);
    Form findById(int id);
    void save(int id);
}
