package com.springboot.angular.coffeesystem.service.drinkPrice;

import com.springboot.angular.coffeesystem.dto.DrinkPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface DrinkPriceService {
    ResponseDto createPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto);
    ResponseDto changePriceOrInitialPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto);
    ResponseDto getPriceOfDrink(Integer drinkId);

}
