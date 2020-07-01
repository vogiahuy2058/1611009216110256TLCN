package coffeesystem.repository;

import coffeesystem.model.*;
import coffeesystem.model.embedding.InternalSCDetailId;
import coffeesystem.model.embedding.SupplyContractDetailId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional(rollbackFor = Exception.class)
public interface InternalSCDetailRepository extends JpaRepository<InternalSCDetail, InternalSCDetailId> {
    Optional<InternalSCDetail> findByMaterialAndInternalSCAndEnable
            (Material material, InternalSC internalSC, boolean enable);
    @Query("select max(iscd.internalSCDetailId.id) from InternalSCDetail iscd")
    Integer findMaxId();
    List<InternalSCDetail> findByInternalSCAndEnableOrderByLastModifiedDateDesc(InternalSC internalSC, boolean enable);
    Optional<InternalSCDetail> findByMaterialAndInternalSCAndInternalSCDetailIdIdAndEnable(
            Material material, InternalSC internalSC, Integer id, boolean enable);
    Page<InternalSCDetail> findAllByInternalSCAndEnable(InternalSC internalSC, boolean enable, Pageable pageable);
    Optional<InternalSCDetail> findByInternalSCDetailIdId(Integer id);
    Optional<InternalSCDetail> findByInternalSCDetailIdIdAndEnable(Integer id, boolean enable);

}
