package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BinFeatureService {

    List<BinFeature>getAllBinFeature();
    Page<BinFeature> getBinFeaturePaginated(Pageable pageable);
    Page<BinFeature> getInboundListPaginated(Pageable pageable);
}
