package com.springboot.angular.coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponseDto {

    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String birthDay;
    private boolean sex;
    private String email;
    private String note;
    private float totalPurchase;

    private String customerType;
}
