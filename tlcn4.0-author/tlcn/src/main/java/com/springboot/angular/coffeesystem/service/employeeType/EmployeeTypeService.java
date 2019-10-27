package com.springboot.angular.coffeesystem.service.employeeType;

import com.springboot.angular.coffeesystem.dto.EmployeeTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface EmployeeTypeService {
    ResponseDto createEmployeeType(EmployeeTypeDto employeeTypeDto);
    ResponseDto getAllEmployeeType();
    ResponseDto deleteEmployeeType(Integer id);
    ResponseDto editEmployeeType(EmployeeTypeDto employeeTypeDto);
    ResponseDto getEmployeeTypeById(Integer id);
    PagingResponseDto getAllEmployeeTypePaging(int page, int size, String sort, String sortColumn);
}
