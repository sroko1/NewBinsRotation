package com.example.NewBinsRotation.services;


import com.example.NewBinsRotation.models.BinFeature;

import com.example.NewBinsRotation.repositories.BinFeatureRepository;

import com.example.NewBinsRotation.repositories.InboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinFeatureServiceImpl implements BinFeatureService {

@Autowired
    InboundRepository inboundRepository;
    @Autowired
    BinFeatureRepository binFeatureRepository;

    @Override
    public List<BinFeature> getAllBinFeature() {
        return binFeatureRepository.findAll();
    }

    @Override
    public Page<BinFeature> getBinFeaturePaginated(Pageable pageable) {
        return binFeatureRepository.findAll(pageable);
    }




}
