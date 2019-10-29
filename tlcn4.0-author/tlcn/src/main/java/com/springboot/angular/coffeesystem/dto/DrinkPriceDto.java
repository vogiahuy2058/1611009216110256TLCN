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
public class DrinkPriceDto {
    private Integer drinkId;
    private String date;
    private float price;
    private float initialPrice;
}
