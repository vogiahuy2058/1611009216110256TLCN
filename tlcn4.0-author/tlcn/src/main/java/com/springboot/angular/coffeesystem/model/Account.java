package com.springboot.angular.coffeesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Account extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private boolean enable = true;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    private Set<Role> role = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Account(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
