package com.example.NewBinsRotation.services;


import com.example.NewBinsRotation.models.BinFeature;

import com.example.NewBinsRotation.repositories.BinFeatureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinFeatureServiceImpl implements BinFeatureService {


    @Autowired
    BinFeatureRepository binFeatureRepository;

    @Override
    public Page<BinFeature> getBinFeaturePaginated(Pageable pageable) {
        return binFeatureRepository.findAll(pageable);
    }

    @Override
    public List<BinFeature>getAllBinFeature() {
      return (List<BinFeature>)  binFeatureRepository.findAll();
    }

    @Override
    public void addNewBinFeature(BinFeature binFeature) {
        binFeatureRepository.save(binFeature);
    }

    @Override
    public void deleteBinFeature(int id) {
        binFeatureRepository.deleteById(id);
    }

    @Override
    public Optional<BinFeature> getBinFeatureById(Integer id) {
        return binFeatureRepository.findById(id);
    }

    @Override
    public void editBinFeature(BinFeature binFeature) {
        binFeatureRepository.save(binFeature);
    }

    @Override
    public void save(BinFeature binFeature) {
        binFeatureRepository.save(binFeature);
    }


}
    //@Override
   // public  void save(BinFeature binFeature){
     //   binFeatureRepository.save(binFeature);
   // }


