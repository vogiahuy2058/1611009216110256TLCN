package com.springboot.angular.coffeesystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DrinkDto {
    private Integer id;
    private String name;
    private String description;
    private String drinkType;

}
