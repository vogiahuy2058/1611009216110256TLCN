package com.springboot.angular.coffeesystem.service.unit;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.UnitDto;

public interface UnitService {
    ResponseDto createUnit(UnitDto unitDto);
    ResponseDto getAllUnit();
    ResponseDto deleteUnit(Integer id);
    ResponseDto editUnit(UnitDto unitDto);
    ResponseDto getUnitById(Integer id);
    PagingResponseDto getAllUnitPaging(int page, int size, String sort, String sortColumn);
}
