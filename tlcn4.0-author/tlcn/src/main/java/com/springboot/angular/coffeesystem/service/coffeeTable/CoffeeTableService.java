package com.springboot.angular.coffeesystem.service.coffeeTable;

import com.springboot.angular.coffeesystem.dto.CoffeeTableDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.CoffeeTable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CoffeeTableService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto createCoffeeTable(CoffeeTableDto coffeeTableDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto getAllCoffeeTable();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto deleteCoffeeTable(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto editCoffeeTable(CoffeeTableDto coffeeTableDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto getCoffeeTableById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllCoffeeTablePaging(int page, int size, String sort, String sortColumn);
}
