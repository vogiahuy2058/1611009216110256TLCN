package com.springboot.angular.coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDetailResponseDto {
    private Integer amount;
    private float price;
    private float discount;
    private float unitPrice;
    private Integer serial;
    private Integer invoiceId;
    private Integer drinkId;
    private String drinkName;
}
