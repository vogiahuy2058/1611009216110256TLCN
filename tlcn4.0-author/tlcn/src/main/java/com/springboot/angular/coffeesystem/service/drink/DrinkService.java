package com.springboot.angular.coffeesystem.service.drink;

import com.springboot.angular.coffeesystem.dto.DrinkDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DrinkService {
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    ResponseDto getAllDrinkByDrinkType(String name);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    ResponseDto getAllDrink();
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    PagingResponseDto getAllDrinkPaging(int page,int size,String sort,String sortColumn);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createDrink(DrinkDto drinkDTO);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto editDrink(DrinkDto drinkDto);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto deleteDrink(Integer id);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    ResponseDto getDrinkById(Integer id);
}
