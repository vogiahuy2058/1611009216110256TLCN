package coffeesystem.repository;


import coffeesystem.model.Material;
import coffeesystem.model.SupplyContract;
import coffeesystem.model.SupplyContractDetail;
import coffeesystem.model.embedding.SupplyContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface SupplyContractDetailRepository extends JpaRepository<SupplyContractDetail, SupplyContractId> {
    Optional<SupplyContractDetail> findByMaterialAndSupplyContract(Material material, SupplyContract supplyContract);
}
