package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerType extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NaturalId
    private String name;
    private boolean enable = true;
    private String discountName;
    private double discountValue;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerType", cascade = CascadeType.ALL)
    private Set<Customer> customers = new HashSet<>();

}
