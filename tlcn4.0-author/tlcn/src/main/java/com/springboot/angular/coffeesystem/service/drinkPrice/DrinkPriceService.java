package com.springboot.angular.coffeesystem.service.drinkPrice;

import com.springboot.angular.coffeesystem.dto.DrinkPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DrinkPriceService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto changePriceOrInitialPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getPriceOfDrink(Integer drinkId);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getAllPriceOfDrink();
}
