package com.springboot.angular.coffeesystem.model;

import com.springboot.angular.coffeesystem.model.embedding.InvoiceDetailId;
import com.springboot.angular.coffeesystem.model.embedding.SupplyContractId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "supply_contract_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplyContractDetail extends Auditable<String> {
    @EmbeddedId
    private SupplyContractId id;

    @ManyToOne
    @MapsId("materialId")
    private Material material;

    @ManyToOne
    @MapsId("supplyContractId")
    private SupplyContract supplyContract;

    private float unitPrice;
    private float amount;
    private LocalDate deliveryTime;
    private LocalDate paymentTime;

}
