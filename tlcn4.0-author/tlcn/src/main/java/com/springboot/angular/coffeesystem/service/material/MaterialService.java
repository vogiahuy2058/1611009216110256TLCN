package com.springboot.angular.coffeesystem.service.material;

import com.springboot.angular.coffeesystem.dto.MaterialDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface MaterialService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto createMaterial(MaterialDto materialDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllMaterial();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto deleteMaterial(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editMaterial(MaterialDto materialDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getMaterialById(Integer id);
    //    ResponseDto changePrice(Integer id, float newPrice);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllMaterialPaging(int page, int size, String sort, String sortColumn);
}
