package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "material")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Material extends Auditable<String>{

//    @EmbeddedId
//    private MaterialPriceId materialId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean enable = true;
    private float inventory;
    private float minInventory;
    private float maxInventory;


    @ManyToOne
    @JoinColumn(name = "material_type_id")
    private MaterialType materialType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
    private Set<SupplyContractDetail> supplyContractDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "material", cascade = CascadeType.ALL)
    private Set<MaterialPrice> materialPrices;
}
