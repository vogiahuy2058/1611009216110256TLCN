package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drink")
public class Drink extends Auditable<String> {

//    @EmbeddedId
//    private DrinkPriceId drinkId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean enable = true;
    private String description;

    @ManyToOne
    @JoinColumn(name = "drinkType_id")
    private DrinkType drinkType;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drink", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drink", cascade = CascadeType.ALL)
    private Set<InvoiceDetail> invoiceDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drink", cascade = CascadeType.ALL)
    private Set<DrinkPrice> drinkPrices;

}
