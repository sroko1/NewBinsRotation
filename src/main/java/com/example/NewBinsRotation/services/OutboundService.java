package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Outbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OutboundService {

    List<Outbound>getAllOutbound();
    Page<Outbound>getAllOutboundPaginated(Pageable pageable);

    void deleteOutbound(Integer id);

    Optional<Outbound> getOutboundById(Integer id);


    void editOutbound(Outbound outbound);

    void addNewOutbound(Outbound outbound);

}


