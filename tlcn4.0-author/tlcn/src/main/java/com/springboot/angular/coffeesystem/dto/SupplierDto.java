package com.springboot.angular.coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String taxCode;
    private String note;
    private float totalPurchase;
}
