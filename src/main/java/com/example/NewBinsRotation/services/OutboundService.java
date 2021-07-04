package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.Outbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OutboundService {

    List<Outbound>getAllOutbound();
    Page<Outbound>getAllOutboundPaginated(Pageable pageable);
}
