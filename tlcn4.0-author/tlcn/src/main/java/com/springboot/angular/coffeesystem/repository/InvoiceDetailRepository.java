package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Drink;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.InvoiceDetail;
import com.springboot.angular.coffeesystem.model.embedding.InvoiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailId> {
    Optional<InvoiceDetail> findByDrinkAndInvoice(Drink drink, Invoice invoice);
}
