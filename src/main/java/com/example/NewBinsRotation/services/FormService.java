package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FormService {
    List<Form> getAllForm();

    Page<Form> getFormsPaginated(Pageable pageable);

    Optional<Form> getFormById(Integer id);

    void saveForm(Form form);

    void editForm(Form form);

    Form findById(Integer id);

    void save(int id);

    void deleteForm(int id);
    void addNewForm(Integer id);
}

