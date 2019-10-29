package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "unit")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Unit extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    private String name;
    private boolean enable = true;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "unit", cascade = CascadeType.ALL)
    private Material material;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "unit", cascade = CascadeType.ALL)
    private Recipe recipe;
}
