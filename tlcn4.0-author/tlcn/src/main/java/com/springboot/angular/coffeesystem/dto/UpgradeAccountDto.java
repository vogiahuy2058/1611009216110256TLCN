package com.springboot.angular.coffeesystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UpgradeAccountDto {
    private String username;

    private String email;

    private Set<String> role;

//    private String password;

    private Integer idEmployee;
}
