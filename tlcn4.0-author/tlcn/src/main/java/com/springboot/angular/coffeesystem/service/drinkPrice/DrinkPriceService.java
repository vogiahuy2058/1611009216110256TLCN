package com.springboot.angular.coffeesystem.service.drinkPrice;

import com.springboot.angular.coffeesystem.dto.DrinkPriceDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface DrinkPriceService {
    ResponseDto createPriceOfDrink(DrinkPriceDto drinkPriceDto);
    ResponseDto changePriceOrInitialPriceOfDrink(DrinkPriceDto drinkPriceDto);
    ResponseDto getPriceOfDrink(Integer drinkId);

}
