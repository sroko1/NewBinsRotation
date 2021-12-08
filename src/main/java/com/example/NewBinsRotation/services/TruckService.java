package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TruckService {
    List<Truck> getAllTruck();

    Page<Truck> getTrucksPaginated(Pageable pageable);

    void addNewTruck(Truck truck);

    void deleteTruck(int id);

    Truck getTruckById(int id);

    void editTruck(Truck truck);

    void save(Truck truck);

    Truck findByRegNumber( String regNumber);
}
