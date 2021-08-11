package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Supplier;


import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    void addNewSupplier(Supplier supplier);

    void deleteSupplier(int id);

    Supplier getSupplierById(int id);

    void editSupplier(Supplier supplier);

}
