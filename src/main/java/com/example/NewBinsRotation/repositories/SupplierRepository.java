package com.example.NewBinsRotation.repositories;


import com.example.NewBinsRotation.models.Supplier;
import com.example.NewBinsRotation.models.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>  {
    void deleteById(int id);

    Supplier findById(int id);

    void save(int id);

}