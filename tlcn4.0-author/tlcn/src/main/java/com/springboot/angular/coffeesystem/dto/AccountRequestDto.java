package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.Employee;
import com.springboot.angular.coffeesystem.util.IsExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountRequestDto {
    private Integer id;
    @IsExists(message = "Account is already taken!")
    private String username;
    private String password;
    @IsExists
    @Email(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;
    private Set<RoleDto> roleNames = new HashSet<>();
    private Integer idEmployee;
}
