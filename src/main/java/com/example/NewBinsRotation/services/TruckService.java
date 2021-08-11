package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Truck;

import java.util.List;
import java.util.Optional;

public interface TruckService {
    List<Truck>getAllTruck();

    void addNewTruck(Truck truck);

    void deleteTruck(int id);

    Truck getTruckById(int id);

    void editTruck(Truck truck);
}
