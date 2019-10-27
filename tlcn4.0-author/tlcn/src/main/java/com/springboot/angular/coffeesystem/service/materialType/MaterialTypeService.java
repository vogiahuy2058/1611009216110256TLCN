package com.springboot.angular.coffeesystem.service.materialType;

import com.springboot.angular.coffeesystem.dto.MaterialTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface MaterialTypeService {
    ResponseDto createMaterialType(MaterialTypeDto materialTypeDto);
    ResponseDto getAllMaterialType();
    ResponseDto deleteMaterialType(Integer id);
    ResponseDto editMaterialType(MaterialTypeDto materialTypeDto);
    ResponseDto getMaterialTypeById(Integer id);
    PagingResponseDto getAllMaterialTypePaging(int page, int size, String sort, String sortColumn);
}
