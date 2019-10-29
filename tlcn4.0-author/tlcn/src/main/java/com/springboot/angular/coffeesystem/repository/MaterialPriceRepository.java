package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.MaterialPrice;
import com.springboot.angular.coffeesystem.model.embedding.MaterialPriceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialPriceRepository extends JpaRepository<MaterialPrice, MaterialPriceId> {
    Optional<MaterialPrice> findByMaterialPriceIdIdAndEnable(Integer id, boolean enable);
}
