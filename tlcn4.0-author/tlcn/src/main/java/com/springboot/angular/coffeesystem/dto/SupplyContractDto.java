package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplyContractDto {
    private Integer id;
    private LocalDate date;
    private float totalPrice;
    private String branchShop;
    private String supplier;


}
