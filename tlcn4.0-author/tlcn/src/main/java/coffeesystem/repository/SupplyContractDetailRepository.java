package coffeesystem.repository;


import coffeesystem.model.*;
import coffeesystem.model.embedding.SupplyContractDetailId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface SupplyContractDetailRepository extends JpaRepository<SupplyContractDetail, SupplyContractDetailId> {
    Optional<SupplyContractDetail> findByMaterialAndSupplyContract
            (Material material, SupplyContract supplyContract);
    @Query("select max(scd.supplyContractDetailId.id) from SupplyContractDetail scd")
    Integer findMaxId();
    List<SupplyContractDetail> findBySupplyContract(SupplyContract supplyContract);
    Optional<SupplyContractDetail> findByMaterialAndSupplyContractAndSupplyContractDetailIdId(
            Material material, SupplyContract supplycontract, Integer id);
    Page<SupplyContractDetail> findAllBySupplyContract(SupplyContract supplyContract, Pageable pageable);
    Optional<SupplyContractDetail> findBySupplyContractDetailIdId(Integer id);
}
