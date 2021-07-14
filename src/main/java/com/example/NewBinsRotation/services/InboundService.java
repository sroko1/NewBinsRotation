package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Inbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InboundService {

    List<Inbound> getAllInbound();

    Page<Inbound> getAllInboundPaginated(Pageable pageable);

    Page<Inbound> getInboundListPaginated(Pageable pageable);


   void deleteInbound(Integer id);

  Optional<Inbound> getInboundById(Integer id);

    void editInbound(Inbound inbound);
    void addNewInbound(Inbound inbound);
}


//wcześniej -  Inbound getInboundById(Integer id);