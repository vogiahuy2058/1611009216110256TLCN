package com.springboot.angular.coffeesystem.dto;


import com.springboot.angular.coffeesystem.model.MaterialType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialDto {
    private Integer id;
    private String name;
    private float inventory;
    private float minInventory;
    private float maxInventory;

    private String materialType;
}
