package com.springboot.angular.coffeesystem.service.employee;

import com.springboot.angular.coffeesystem.dto.EmployeeRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface EmployeeService {
    ResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    ResponseDto getAllEmployee();
    ResponseDto deleteEmployee(Integer id);
    ResponseDto editEmployee(EmployeeRequestDto employeeRequestDto);
    ResponseDto getEmployeeById(Integer id);
    PagingResponseDto getAllEmployeePaging(int page, int size, String sort, String sortColumn);
}
