package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.TableType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeTableDto {
    private Integer id;
    private String name;
    private Integer numberOfChair;
    private String note;

    private String tableType;

}
