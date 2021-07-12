package com.example.NewBinsRotation.services;

import com.example.NewBinsRotation.models.BinFeature;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface BinFeatureService {


    Page<BinFeature> getBinFeaturePaginated(Pageable pageable);
    List<BinFeature>getAllBinFeature();


   void addNewBinFeature(BinFeature binFeature);

    void deleteBinFeature(Integer id);

BinFeature getBinFeatureById(Integer id);

    void editBinFeature(BinFeature binFeature);
}
