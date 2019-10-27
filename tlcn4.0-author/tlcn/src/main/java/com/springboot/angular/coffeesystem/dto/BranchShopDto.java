package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.Customer;
import com.springboot.angular.coffeesystem.model.Employee;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.SupplyContract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BranchShopDto {
    private Integer id;
    private String name;
    private String address;

}
