package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branchShop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchShop extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private boolean enable = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branchShop", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branchShop", cascade = CascadeType.ALL)
    private Set<SupplyContract> supplyContracts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branchShop", cascade = CascadeType.ALL)
    private Set<Employee> employees;

}
