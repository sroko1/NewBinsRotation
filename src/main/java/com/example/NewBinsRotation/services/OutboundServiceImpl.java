package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Outbound;
import com.example.NewBinsRotation.repositories.OutboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OutboundServiceImpl implements OutboundService{

    @Autowired
    OutboundRepository outboundRepository;
    @Override
    public List<Outbound> getAllOutbound() {
        return outboundRepository.findAll();
    }

    @Override
    public Page<Outbound> getAllOutboundPaginated(Pageable pageable) {
        return outboundRepository.findAll(pageable);
    }
}
