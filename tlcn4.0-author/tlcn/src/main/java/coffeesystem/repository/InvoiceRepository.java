package coffeesystem.repository;

import coffeesystem.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByIdAndEnable(Integer id, boolean enable);
    Optional<Invoice> findByIdAndEnableAndStatus(Integer id, boolean enable, Integer status);
    List<Invoice> findByBranchShopId(Integer id);
    List<Invoice> findByOrderTypeId(Integer id);
    List<Invoice> findByCustomerId(Integer id);
//    List<Invoice> findByCoffeeTableId(Integer id);
    Page<Invoice> findAllByEnable(boolean enable, Pageable pageable);
//    @Query("select i.customer as customer from Invoice i where i.enable=?1 and i.paymentStatus=?2")
    List<Invoice> findAllByEnableAndStatus(boolean enable, Integer status);

    Page<Invoice> findAllByEnableAndStatus(boolean enable, Integer status, Pageable pageable);

    @Query("select i from Invoice i where i.enable=?1 and i.status=?2 " +
            "and i.date>=?3 and i.date <=?4")
    Page<Invoice> findByEnableAndPaymentStatusAndDate(boolean enable, Integer status,
                                                      ZonedDateTime fromDate, ZonedDateTime toDate, Pageable pageable);
    @Query("select i from Invoice i where i.enable=?1 and i.status=?2 " +
            "and i.date>=?3 and i.date <=?4")
    List<Invoice> findByEnableAndPaymentStatusAndDate(boolean enable, Integer status,
                                                      ZonedDateTime fromDate, ZonedDateTime toDate);
    List<Invoice> findAllByEnableAndStatusAndBranchShopId(
            boolean enable, Integer status, Integer id);
    @Query("select i from Invoice i where i.enable=?1 and i.status=?2 " +
            "and i.branchShop.id=?3 and i.date>=?4 and i.date <=?5")
    Page<Invoice> findByEnableAndPaymentStatusAndBranchShopIdAndDate(
            boolean enable, Integer status, Integer id,
            ZonedDateTime fromDate, ZonedDateTime toDate, Pageable pageable);
    @Query("select i from Invoice i where i.enable=?1 and i.status=?2 " +
            "and i.branchShop.id=?3 and i.date>=?4 and i.date <=?5")
    List<Invoice> findByEnableAndPaymentStatusAndBranchShopIdAndDate(
            boolean enable, Integer status, Integer id,
            ZonedDateTime fromDate, ZonedDateTime toDate);
    Page<Invoice> findAllByEnableAndStatusAndBranchShopId(
            boolean enable, Integer status, Integer id, Pageable pageable);
    Page<Invoice> findAll(Pageable pageable);
    @Query("select max(i.id) from Invoice i")
    Integer findMaxId();
}
