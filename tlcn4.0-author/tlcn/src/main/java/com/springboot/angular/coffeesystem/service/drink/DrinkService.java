package com.springboot.angular.coffeesystem.service.drink;

import com.springboot.angular.coffeesystem.dto.DrinkDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface DrinkService {
    ResponseDto getAllDrink();

    PagingResponseDto getAllDrinkPaging(int page,int size,String sort,String sortColumn);

    ResponseDto createDrink(DrinkDto drinkDTO);
    ResponseDto editDrink(DrinkDto drinkDto);
    ResponseDto deleteDrink(Integer id);
    ResponseDto getDrinkById(Integer id);
//    ResponseDto changePrice(Integer id, float newPrice);


}
