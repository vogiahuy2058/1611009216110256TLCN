package com.springboot.angular.coffeesystem.service.materialPrice;

import com.springboot.angular.coffeesystem.dto.MaterialPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface MaterialPriceService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createPriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto changePriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getPriceOfMaterial(Integer materialId);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllPriceOfMaterial();
}
