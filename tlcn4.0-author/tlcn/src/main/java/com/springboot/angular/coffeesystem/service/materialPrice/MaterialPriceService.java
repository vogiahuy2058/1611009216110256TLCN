package com.springboot.angular.coffeesystem.service.materialPrice;

import com.springboot.angular.coffeesystem.dto.MaterialPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface MaterialPriceService {
    ResponseDto createPriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto);
    ResponseDto changePriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto);
    ResponseDto getPriceOfMaterial(Integer materialId);
    ResponseDto getAllPriceOfMaterial();
}
