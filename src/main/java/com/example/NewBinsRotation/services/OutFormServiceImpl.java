package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.*;
import com.example.NewBinsRotation.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OutFormServiceImpl implements OutFormService {

    final OutFormRepository outFormRepository;
    final OutboundRepository outboundRepository;
    final BinFeatureRepository binFeatureRepository;
    final TruckRepository truckRepository;
    final SupplierRepository supplierRepository;

    public OutFormServiceImpl(OutFormRepository outFormRepository,
                              OutboundRepository outboundRepository,
                              BinFeatureRepository binFeatureRepository,
                              TruckRepository truckRepository,
                              SupplierRepository supplierRepository) {

        this.outFormRepository = outFormRepository;
        this.outboundRepository = outboundRepository;
        this.binFeatureRepository = binFeatureRepository;
        this.truckRepository = truckRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<OutForm> getAllOutForm() {
        return outFormRepository.findAll();
    }

    @Override
    public Page<OutForm> getOutFormsPaginated(Pageable pageable) {
        return outFormRepository.findAll(pageable);
    }

    @Override
    public Optional<OutForm> getOutFormById(Integer id) {
        return outFormRepository.findById(id);
    }

    @Override
    public void saveOutForm(OutForm outForm) {
        outFormRepository.save(outForm);

    }

    @Override
    public void editOutForm(OutForm outForm) {
        outFormRepository.save(outForm);

    }

    @Override
    public OutForm findById(int id) {
        return outFormRepository.getById(id);
    }

    @Override
    public void save(int id) {
       OutForm outForm = outFormRepository.findById(id);
        outFormRepository.save(outForm);
    }

    @Override
    public List<OutForm> addNewOutForm(Integer id) {
        Optional<OutForm> outForm = outFormRepository.findById(id);
        if (outForm.isPresent()) {
            OutForm ofr = outForm.get();
            outFormRepository.save(ofr);

            BinFeature binFeature = binFeatureRepository.getById(ofr.getBinFeatures().getId());
           binFeature.setAmount(binFeature.getAmount() - ofr.getAmount());
            binFeatureRepository.save(binFeature);
        }
        return outFormRepository.findAll();
    }

    @Override
    public void deleteOutForm ( int id){
        OutForm outForm = outFormRepository.findById(id);
        BinFeature binFeature = binFeatureRepository.getById(outForm.getBinFeatures().getId());

            binFeature.setAmount(binFeature.getAmount() + outForm.getAmount());
            binFeatureRepository.save(binFeature);
            outFormRepository.deleteById(id);
        }
    }



