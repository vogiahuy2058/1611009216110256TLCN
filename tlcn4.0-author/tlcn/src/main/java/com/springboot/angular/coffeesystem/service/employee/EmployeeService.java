package com.springboot.angular.coffeesystem.service.employee;

import com.springboot.angular.coffeesystem.dto.EmployeeRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface EmployeeService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllEmployee();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto deleteEmployee(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto editEmployee(EmployeeRequestDto employeeRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getEmployeeById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllEmployeePaging(int page, int size, String sort, String sortColumn);
}
