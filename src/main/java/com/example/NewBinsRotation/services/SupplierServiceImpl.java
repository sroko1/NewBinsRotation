package com.example.NewBinsRotation.services;


import com.example.NewBinsRotation.models.Supplier;
import com.example.NewBinsRotation.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService{

    final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public void addNewSupplier(Supplier supplier) {
        supplierRepository.save(supplier);

    }

    @Override
    public void deleteSupplier(int id) {
        supplierRepository.deleteById(id);

    }

    @Override
    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void editSupplier(Supplier supplier) {
        supplierRepository.save(supplier);

    }
}
