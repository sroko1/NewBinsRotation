package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.repositories.InboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class InboundServiceImpl implements InboundService {

    @Autowired
    InboundRepository inboundRepository;

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
    @Override
    public void addNewInbound(Inbound inbound){
        inboundRepository.save(inbound);
    }
}