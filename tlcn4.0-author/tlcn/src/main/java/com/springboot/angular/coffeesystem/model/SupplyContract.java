package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "supply_contract")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplyContract extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private float totalPrice;
    private boolean enable = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplyContract", cascade = CascadeType.ALL)
    private Set<SupplyContractDetail> supplyContractDetails;

    @ManyToOne
    @JoinColumn(name = "branch_shop")
    private BranchShop branchShop;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
