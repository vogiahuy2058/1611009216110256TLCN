package coffeesystem.repository;

import coffeesystem.model.InternalSC;
import coffeesystem.model.SupplyContract;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InternalSCRepository extends JpaRepository<InternalSC, Integer> {
    List<InternalSC> findAllByEnable(boolean enable);
    Page<InternalSC> findAllByEnable(boolean enable, Pageable pageable);
    Optional<InternalSC> findByIdAndEnable(Integer id, boolean enable);

//    @Query("select max(ii.id) from InternalSC ii  where ii in " +
//            "(SELECT isc FROM InternalSC isc where isc.branchShop.id=?1 and isc.enable=?2 and isc.status=?3) " )
//    Optional<InternalSC> findInternalSCHaveMaxIdByBranchShopIdAndEnableAndStatus(Integer id, boolean enable, Integer status);
    @Query("select max(ii.id) from InternalSC ii  where ii.branchShop.id=?1 and ii.enable=?2 and ii.status=?3" )
    Integer findMaxIdByBranchShopIdAndEnableAndStatus(Integer id, boolean enable, Integer status);
    List<InternalSC> findById(List<Integer> id);
    List<InternalSC> findByBranchShopId(Integer id);
    List<InternalSC> findByBranchShopIdAndStatusAndEnable(Integer id, Integer status, boolean enable);
    List<InternalSC> findAllByEnableAndStatus(boolean enable, Integer status);
    Optional<InternalSC> findByIdAndEnableAndStatus(Integer id, boolean enable, Integer status);
//    @Query("select isc from InternalSC isc where isc.status=?1 and isc.enable=?2 and isc.")
    List<InternalSC> findByBranchShopIdAndStatusAndDateCreateLessThanAndEnable(Integer id, Integer status,LocalDate dateCreate, boolean enable);
    @Query("select max(isc.id) from InternalSC isc")
    Integer findMaxId();
}
