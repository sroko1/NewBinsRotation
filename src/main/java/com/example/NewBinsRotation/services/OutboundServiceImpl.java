package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Outbound;
import com.example.NewBinsRotation.repositories.OutboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutboundServiceImpl implements OutboundService{

    final
    OutboundRepository outboundRepository;

    public OutboundServiceImpl(OutboundRepository outboundRepository) {
        this.outboundRepository = outboundRepository;
    }

    @Override
    public List<Outbound> getAllOutbound() {
        return outboundRepository.findAll();
    }

    @Override
    public Page<Outbound> getAllOutboundPaginated(Pageable pageable) {
        return outboundRepository.findAll(pageable);
    }

    @Override
    public void deleteOutbound(Integer id) {
       outboundRepository.deleteById(id);

    }

    @Override
    public Optional<Outbound> getOutboundById(Integer id) {
        return outboundRepository.findById(id);
    }

    @Override
    public void editOutbound(Outbound outbound) {
      outboundRepository.save(outbound);

    }
    @Override
    public void addNewOutbound(Outbound outbound){
        outboundRepository.save(outbound);
    }
}



