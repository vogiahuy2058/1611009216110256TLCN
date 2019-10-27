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
@Table(name = "roleNames")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    @Enumerated(EnumType.STRING)
    private RoleName name;
    private boolean enable = true;
    @ManyToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

}
