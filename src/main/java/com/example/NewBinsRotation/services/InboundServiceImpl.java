package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.repositories.InboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


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
}