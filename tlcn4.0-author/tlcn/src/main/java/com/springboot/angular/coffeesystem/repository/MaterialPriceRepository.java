package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.MaterialPrice;
import com.springboot.angular.coffeesystem.model.embedding.MaterialPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MaterialPriceRepository extends JpaRepository<MaterialPrice, MaterialPriceId> {
//    @Query("select mp from MaterialPrice mp where mp.materialPriceId.idMaterial=?1 and mp.enable=?2")
    Optional<MaterialPrice> findByMaterialPriceIdIdMaterialAndEnable(Integer idMaterial, boolean enable);
    @Query("select max(mp.materialPriceId.id) from MaterialPrice mp")
    Integer findMaxId();
}
