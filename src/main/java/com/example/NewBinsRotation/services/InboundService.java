package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.PortalUser;
import com.example.NewBinsRotation.models.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InboundService {

    List<Inbound> getAllInbound();

    Page<Inbound> getAllInboundPaginated(Pageable pageable);

    Page<Inbound> getInboundListPaginated(Pageable pageable);


   void deleteInbound(Integer id);

  Optional<Inbound> getInboundById(Integer id); /// tutaj

    void editInbound(Inbound inbound);
   void addNewInbound(Inbound inbound);

    List<Inbound>addNewData(Integer id);
    void save(Inbound inbound);
    void saveData( Inbound inbound, BinFeature binFeature, Truck truck);
}


//wcześniej -  Inbound getInboundById(Integer id);