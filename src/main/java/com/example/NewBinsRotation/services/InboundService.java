package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Inbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InboundService {

    List<Inbound>getAllInbound();
    Page<Inbound>getAllInboundPaginated(Pageable pageable);
    Page<Inbound> getInboundListPaginated(Pageable pageable);
}
