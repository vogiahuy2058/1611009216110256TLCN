package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.SupplyContract;
import com.springboot.angular.coffeesystem.model.SupplyContractDetail;
import com.springboot.angular.coffeesystem.model.embedding.SupplyContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface SupplyContractDetailRepository extends JpaRepository<SupplyContractDetail, SupplyContractId> {
    Optional<SupplyContractDetail> findByMaterialAndSupplyContract(Material material, SupplyContract supplyContract);
}
