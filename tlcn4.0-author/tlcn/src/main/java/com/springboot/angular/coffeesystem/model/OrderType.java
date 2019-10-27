package com.springboot.angular.coffeesystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderType extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean enable = true;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderType", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;
}
