package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Form;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.OutForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OutFormService {
    List<OutForm> getAllOutForm();

    Page<OutForm> getOutFormsPaginated(Pageable pageable);

    Optional<OutForm> getOutFormById(Integer id);

    void saveOutForm(OutForm outForm);

    void editOutForm(OutForm outForm);

    OutForm findById(int id);

    void save(int id);

    void deleteOutForm(int id);
    void addNewOutForm(Integer id);
}