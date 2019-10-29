package com.springboot.angular.coffeesystem.service.materialPrice;

import com.springboot.angular.coffeesystem.dto.MaterialDto;
import com.springboot.angular.coffeesystem.dto.MaterialPriceDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface MaterialPriceService {
    ResponseDto createPriceOfMaterial(MaterialPriceDto materialPriceDto);
    ResponseDto changePriceOfMaterial(MaterialPriceDto materialPriceDto);
    ResponseDto getPriceOfMaterial(Integer materialId);
}
