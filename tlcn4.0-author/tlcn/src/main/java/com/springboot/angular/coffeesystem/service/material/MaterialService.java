package com.springboot.angular.coffeesystem.service.material;

import com.springboot.angular.coffeesystem.dto.MaterialDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface MaterialService {
    ResponseDto createMaterial(MaterialDto materialDto);
    ResponseDto getAllMaterial();
    ResponseDto deleteMaterial(Integer id);
    ResponseDto editMaterial(MaterialDto materialDto);
    ResponseDto getMaterialById(Integer id);
//    ResponseDto changePrice(Integer id, float newPrice);
    PagingResponseDto getAllMaterialPaging(int page, int size, String sort, String sortColumn);
}
