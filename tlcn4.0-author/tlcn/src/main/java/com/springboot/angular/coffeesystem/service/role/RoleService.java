package com.springboot.angular.coffeesystem.service.role;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.RoleDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RoleService {

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getAllRole();
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto deleteRole(Integer id);
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getRoleById(Integer id);
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    PagingResponseDto getAllRolePaging(int page, int size, String sort, String sortColumn);
}
