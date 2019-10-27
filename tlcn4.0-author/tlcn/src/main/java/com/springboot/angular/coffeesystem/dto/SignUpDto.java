package com.springboot.angular.coffeesystem.dto;


import com.springboot.angular.coffeesystem.util.IsExists;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Getter
@Setter
public class SignUpDto {
    @IsExists(message = "Account is already taken!")
    private String username;

    @IsExists
    @Email(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;

    private Set<String> role;

    private String password;

    private Integer idEmployee;

}
