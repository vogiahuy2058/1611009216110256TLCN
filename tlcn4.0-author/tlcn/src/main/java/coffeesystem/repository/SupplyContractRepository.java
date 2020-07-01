package coffeesystem.repository;


import coffeesystem.model.Invoice;
import coffeesystem.model.SupplyContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface SupplyContractRepository extends JpaRepository<SupplyContract, Integer> {
    List<SupplyContract> findAllByEnable(boolean enable);
    Page<SupplyContract> findAllByEnable(boolean enable, Pageable pageable);
    Optional<SupplyContract> findByIdAndEnable(Integer id, boolean enable);
    List<SupplyContract> findByBranchShopId(Integer id);
    List<SupplyContract> findBySupplierIdAndEnable(Integer id, boolean enable);
    List<SupplyContract> findAllByEnableAndStatus(boolean enable, Integer status);
    Optional<SupplyContract> findByIdAndEnableAndStatus(Integer id, boolean enable, Integer status);
}
