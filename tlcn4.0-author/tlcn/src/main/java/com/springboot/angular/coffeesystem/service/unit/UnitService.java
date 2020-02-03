package com.springboot.angular.coffeesystem.service.unit;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.UnitDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UnitService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto createUnit(UnitDto unitDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllUnit();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto deleteUnit(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editUnit(UnitDto unitDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getUnitById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllUnitPaging(int page, int size, String sort, String sortColumn);
}
