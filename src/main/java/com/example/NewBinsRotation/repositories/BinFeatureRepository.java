package com.example.NewBinsRotation.repositories;

import com.example.NewBinsRotation.models.BinFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BinFeatureRepository extends JpaRepository<BinFeature, Integer> {

   void deleteById(Integer id);
   Optional<BinFeature> findById(Integer id);
}
