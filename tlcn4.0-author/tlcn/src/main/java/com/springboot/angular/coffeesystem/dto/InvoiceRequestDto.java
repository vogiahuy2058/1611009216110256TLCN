package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceRequestDto {
    private Integer id;
    private float VAT;
    private float totalPrice;
    private LocalDateTime date;
    private float totalDiscount;
    private String customerPhone;

    private String branchShop;

    private String coffeeTable;

    private String orderType;

}
