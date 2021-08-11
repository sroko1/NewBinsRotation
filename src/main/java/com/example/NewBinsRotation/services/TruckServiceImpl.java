package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.repositories.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TruckServiceImpl implements TruckService{

    final TruckRepository truckRepository;

    public TruckServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public List<Truck> getAllTruck() {
        return truckRepository.findAll();
    }

    @Override
    public void addNewTruck(Truck truck) {
        truckRepository.save(truck);
    }

    @Override
    public void deleteTruck(int id) {
        truckRepository.deleteById(id);

    }

    @Override
    public Truck getTruckById(int id) {
        return truckRepository.findById(id);
    }

    @Override
    public void editTruck(Truck truck) {
        truckRepository.save(truck);

    }
}
