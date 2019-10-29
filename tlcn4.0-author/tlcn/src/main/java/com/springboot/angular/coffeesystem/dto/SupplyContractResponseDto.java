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
public class SupplyContractResponseDto {
    private Integer id;
    private String date;
    private float totalPrice;
    private String branchShop;
    private String supplier;

}
