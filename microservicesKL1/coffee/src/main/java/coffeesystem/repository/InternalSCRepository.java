package coffeesystem.repository;

import coffeesystem.model.InternalSC;
import coffeesystem.model.SupplyContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InternalSCRepository extends JpaRepository<InternalSC, Integer> {
    List<InternalSC> findAllByEnable(boolean enable);
    Page<InternalSC> findAllByEnable(boolean enable, Pageable pageable);
    Optional<InternalSC> findByIdAndEnable(Integer id, boolean enable);
    List<InternalSC> findByBranchShopId(Integer id);
    List<InternalSC> findAllByEnableAndStatus(boolean enable, Integer status);
    Optional<InternalSC> findByIdAndEnableAndStatus(Integer id, boolean enable, Integer status);
}
