package com.springboot.angular.coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceAndInvoiceDetailDto {
    private Integer id;
    private String date;
    private String customerPhone;
    private String customerName;
    private String branchShop;
    private String cashierName;
    private String orderType;
    private Integer numberPosition;
    private float vat;
    private float totalDiscount;
    private float totalPrice;
    private float realPay;
    List<InvoiceDetailResponseDto> invoiceDetailDtoList;
}
