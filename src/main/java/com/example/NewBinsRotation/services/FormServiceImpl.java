package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Form;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FormServiceImpl implements FormService {

    final FormRepository formRepository;
    final BinFeatureRepository binFeatureRepository;
    final TruckRepository truckRepository;
    final InboundRepository inboundRepository;
    final SupplierRepository supplierRepository;

    public FormServiceImpl(FormRepository formRepository,
                           BinFeatureRepository binFeatureRepository,
                           TruckRepository truckRepository, InboundRepository inboundRepository,
                           SupplierRepository supplierRepository) {

        this.formRepository = formRepository;
        this.binFeatureRepository = binFeatureRepository;
        this.truckRepository = truckRepository;
        this.inboundRepository = inboundRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Form> getAllForm() {
        return formRepository.findAll();
    }

    @Override
    public Page<Form> getFormsPaginated(Pageable pageable) {
        return formRepository.findAll(pageable);
    }

    @Override
    public Optional<Form> getFormById(Integer id) {
        return formRepository.findById(id);
    }

    @Override
    public void saveForm(Form form) {
        formRepository.save(form);

    }

    @Override
    public void editForm(Form form) {
        formRepository.save(form);

    }

    @Override
    public Form findById(Integer id) {
        return formRepository.getById(id);
    }

    @Override
    public void save(int id) {
        Form form = formRepository.findById(id);
        formRepository.save(form);
    }

    @Override
    public List<Form> addNewForm(Integer id) {
        Optional<Form> form = formRepository.findById(id);
        if (form.isPresent()) {
            Form fr = form.get();
            formRepository.save(fr);

            BinFeature binFeature = binFeatureRepository.getById(fr.getBinFeatures().getId());
            binFeature.setId(binFeature.getId());
                binFeature.setAmount(binFeature.getAmount() + fr.getAmount());
                binFeatureRepository.save(binFeature);
                Truck truck = truckRepository.getById(fr.getTrucks().getId());
                truck.setRegNumber(truck.getRegNumber());
                truckRepository.save(truck);
            }
            return formRepository.findAll();
        }

        @Override
        public void deleteForm ( int id){
            Form form = formRepository.findById(id);
            BinFeature binFeature = binFeatureRepository.getById(form.getBinFeatures().getId());

                binFeature.setAmount(binFeature.getAmount() - form.getAmount());
                binFeatureRepository.save(binFeature);
                formRepository.deleteById(id);
            }
        }



