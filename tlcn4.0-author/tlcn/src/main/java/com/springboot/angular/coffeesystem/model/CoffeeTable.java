package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "coffee_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoffeeTable extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer numberOfChair;
    private boolean enable = true;
    private String note;
    @ManyToOne
    @JoinColumn
    private TableType tableType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coffeeTable", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;
}
