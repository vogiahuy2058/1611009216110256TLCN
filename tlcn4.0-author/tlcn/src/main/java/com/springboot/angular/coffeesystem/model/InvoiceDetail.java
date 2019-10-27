package com.springboot.angular.coffeesystem.model;

import com.springboot.angular.coffeesystem.model.embedding.InvoiceDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invoice_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDetail extends Auditable<String> {
    @EmbeddedId
    private InvoiceDetailId invoiceDetailId;
    @ManyToOne
    @MapsId("invoiceId")
    private Invoice invoice;

    @ManyToOne
    @MapsId("drinkId")
    private Drink drink;
    private Integer amount;
    private float price;//total price for each drink
    private float unitPrice;
    private float discount;

}
