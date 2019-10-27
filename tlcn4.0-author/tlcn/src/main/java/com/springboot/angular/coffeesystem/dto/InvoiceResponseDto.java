package com.springboot.angular.coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceResponseDto {
    private Integer id;
    private float VAT;
    private float totalPrice;
    private LocalDateTime date;
    private float totalDiscount;
    private String customerPhone;
    private String customerName;

    private String branchShop;

    private String coffeeTable;

    private String orderType;
}
