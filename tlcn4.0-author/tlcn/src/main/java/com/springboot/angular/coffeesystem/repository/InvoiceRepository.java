package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByIdAndEnable(Integer id, boolean enable);
    List<Invoice> findByBranchShopId(Integer id);
    List<Invoice> findByOrderTypeId(Integer id);
    List<Invoice> findByCustomerId(Integer id);
//    List<Invoice> findByCoffeeTableId(Integer id);
    Page<Invoice> findAllByEnable(boolean enable, Pageable pageable);
//    @Query("select i.customer as customer from Invoice i where i.enable=?1 and i.paymentStatus=?2")
    List<Invoice> findAllByEnableAndPaymentStatus(boolean enable, boolean paymentStatus);
    Page<Invoice> findAllByEnableAndPaymentStatus(boolean enable, boolean paymentStatus, Pageable pageable);
    List<Invoice> findAllByEnableAndPaymentStatusAndBranchShopId(
            boolean enable, boolean paymentStatus, Integer id);
    Page<Invoice> findAllByEnableAndPaymentStatusAndBranchShopId(
            boolean enable, boolean paymentStatus, Integer id, Pageable pageable);
    Page<Invoice> findAll(Pageable pageable);
    @Query("select max(i.id) from Invoice i")
    Integer findMaxId();
}
