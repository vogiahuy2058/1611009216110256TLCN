package com.springboot.angular.coffeesystem.service.drinkType;

import com.springboot.angular.coffeesystem.dto.CustomerTypeDto;
import com.springboot.angular.coffeesystem.dto.DrinkTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface DrinkTypeService {
    ResponseDto createDrinkType(DrinkTypeDto drinkTypeDto);
    ResponseDto getAllDrinkType();
    ResponseDto deleteDrinkType(Integer id);
    ResponseDto editDrinkType(DrinkTypeDto drinkTypeDto);
    ResponseDto getDrinkTypeById(Integer id);
    PagingResponseDto getAllDrinkTypePaging(int page, int size, String sort, String sortColumn);
}
