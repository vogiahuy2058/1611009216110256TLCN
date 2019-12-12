package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.util.IsExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private Integer id;
    private String name;
    @IsExists
    @Email(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;
    private String phone;
    private String address;
    private String taxCode;
    private String note;
    private float totalPurchase;
}
