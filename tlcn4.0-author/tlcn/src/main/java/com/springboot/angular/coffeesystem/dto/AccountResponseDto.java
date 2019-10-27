package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountResponseDto {
    private Integer id;
    private String username;
    private String email;
    private Set<RoleDto> role = new HashSet<>();
    private Integer idEmployee;


}
