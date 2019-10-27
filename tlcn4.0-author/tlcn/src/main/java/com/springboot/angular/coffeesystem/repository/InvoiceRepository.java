package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Invoice> findByCoffeeTableId(Integer id);
    Page<Invoice> findAllByEnable(boolean enable, Pageable pageable);
}
