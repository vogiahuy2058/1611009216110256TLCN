package com.springboot.angular.coffeesystem.service.customerType;

import com.springboot.angular.coffeesystem.dto.CustomerTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface CustomerTypeService {
    ResponseDto createCustomerType(CustomerTypeDto customerTypeDto);
    ResponseDto getAllCustomerType();
    ResponseDto deleteCustomerType(Integer id);
    ResponseDto getCustomerTypeById(Integer id);
    ResponseDto editCustomerType(CustomerTypeDto customerTypeDto);
    PagingResponseDto getAllCustomerTypePaging(int page, int size, String sort, String sortColumn);
}
