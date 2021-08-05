package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.repositories.BinFeatureRepository;
import com.example.NewBinsRotation.repositories.InboundRepository;
import com.example.NewBinsRotation.repositories.TruckRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InboundServiceImpl implements InboundService {

    final InboundRepository inboundRepository;
    final BinFeatureRepository binFeatureRepository;
    final TruckRepository truckRepository;

    public InboundServiceImpl(InboundRepository inboundRepository, BinFeatureRepository binFeatureRepository, TruckRepository truckRepository) {
        this.inboundRepository = inboundRepository;
        this.binFeatureRepository = binFeatureRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public List<Inbound> getAllInbound() {
        return inboundRepository.findAll();
    }

    @Override
    public Page<Inbound> getAllInboundPaginated(Pageable pageable) {
        return inboundRepository.findAll(pageable);
    }

    @Override
    public Page<Inbound> getInboundListPaginated(Pageable pageable) {
        return inboundRepository.findAll(pageable);
    }


    @Override
    public void deleteInbound(Integer id) {
        inboundRepository.deleteById(id);
    }

    @Override
    public Optional<Inbound> getInboundById(Integer id) {
        return inboundRepository.findById(id);

    }

    @Override
    public void editInbound(Inbound inbound) {
        inboundRepository.save(inbound);

    }

  /*  @Override
    public void addNewInbound(Integer id) {

        Inbound inbound = inboundRepository.getById(id);
        BinFeature binFeature = binFeatureRepository.getById(getInboundById(id));

        binFeature.setAmount(binFeature.getAmount() + getInboundById(binFeature.getAmount()));

        binFeatureRepository.save(binFeature);
        inboundRepository.save(inbound);
        binFeatureRepository.findAll();
    }
*/

    @Override
    public void addNewInbound(Inbound inbound) {
        inboundRepository.save(inbound);

    }

    @Override
    public void save(Inbound inbound) {
        inboundRepository.save(inbound);
    }

    @Override
    public void saveData(Inbound inbound, BinFeature binFeature, Truck truck) {

        inboundRepository.save(inbound);
        binFeatureRepository.save(binFeature);
        truckRepository.save(truck);
    }


    @Override
    public List<Inbound> addNewData(Integer id) {
        Optional<Inbound> inbound = inboundRepository.findById(id);
       // if (inbound.isPresent()) {
           // Inbound inb = inbound.get();
        Inbound inb = new Inbound();
        inboundRepository.save(inb);

            BinFeature binFeature = binFeatureRepository.getById(inb.getId());
            binFeature.setName(binFeature.getName());
            binFeature.setAmount(binFeature.getAmount() + inb.getAmount());
            binFeatureRepository.save(binFeature);
            Truck truck = truckRepository.findAll().get(id);
            truck.setRegNumber(truck.getRegNumber());
            truckRepository.save(truck);

        return inboundRepository.findAll();
    }
}
