package coffeesystem.repository;


import coffeesystem.model.Drink;
import coffeesystem.model.Invoice;
import coffeesystem.model.InvoiceDetail;
import coffeesystem.model.embedding.InvoiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailId> {
    Optional<InvoiceDetail> findByDrinkAndInvoiceAndInvoiceDetailIdId(Drink drink, Invoice invoice, Integer id);
    List<InvoiceDetail> findByInvoice(Invoice invoice);
    @Query("select max(ind.invoiceDetailId.id) from InvoiceDetail ind")
    Integer findMaxId();
    Optional<InvoiceDetail> findByInvoiceDetailIdId(Integer id);
}
