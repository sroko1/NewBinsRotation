package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Form;
import com.example.NewBinsRotation.models.Inbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FormService {
    List<Form> getAllForm();

    Page<Form> getFormsPaginated(Pageable pageable);

    Optional<Form> getFormById(Integer id);

    void saveForm(Form form);

    void updateForm(Form form);

    Form findById(int id);

    void save(int id);

    void deleteForm(int id);
    List<Form>addNewForm(Integer id);
}

