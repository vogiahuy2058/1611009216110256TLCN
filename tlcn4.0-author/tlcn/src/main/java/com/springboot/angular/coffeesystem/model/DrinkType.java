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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drink_type")
public class DrinkType extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    private String name;
    private boolean enable = true;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drinkType",cascade = CascadeType.ALL)
    private Set<Drink> drinks = new HashSet<>();


}
