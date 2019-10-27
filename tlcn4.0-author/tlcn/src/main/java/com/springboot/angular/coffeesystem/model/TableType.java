package com.springboot.angular.coffeesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "table_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableType extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean enable = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tableType", cascade = CascadeType.ALL)
    private Set<CoffeeTable> coffeeTables;
}
