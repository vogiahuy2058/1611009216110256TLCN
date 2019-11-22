package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceRequestDto {
    private Integer id;
    private float vat;
    private float totalPrice;
    private ZonedDateTime date;
    private float totalDiscount;
    private Integer numberPosition;
    private String customerPhone;
    private boolean paymentStatus;

//    private String branchShop;

//    private String coffeeTable;

    private String orderType;

}
