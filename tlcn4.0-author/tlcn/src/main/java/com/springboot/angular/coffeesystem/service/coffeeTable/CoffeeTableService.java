package com.springboot.angular.coffeesystem.service.coffeeTable;

import com.springboot.angular.coffeesystem.dto.CoffeeTableDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.CoffeeTable;

public interface CoffeeTableService {
    ResponseDto createCoffeeTable(CoffeeTableDto coffeeTableDto);
    ResponseDto getAllCoffeeTable();
    ResponseDto deleteCoffeeTable(Integer id);
    ResponseDto editCoffeeTable(CoffeeTableDto coffeeTableDto);
    ResponseDto getCoffeeTableById(Integer id);
    PagingResponseDto getAllCoffeeTablePaging(int page, int size, String sort, String sortColumn);
}
