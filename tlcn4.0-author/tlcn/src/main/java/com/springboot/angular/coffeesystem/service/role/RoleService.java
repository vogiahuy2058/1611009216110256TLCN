package com.springboot.angular.coffeesystem.service.role;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.RoleDto;

public interface RoleService {
    ResponseDto getAllRole();
    ResponseDto deleteRole(Integer id);
    ResponseDto getRoleById(Integer id);
    PagingResponseDto getAllRolePaging(int page, int size, String sort, String sortColumn);
}
