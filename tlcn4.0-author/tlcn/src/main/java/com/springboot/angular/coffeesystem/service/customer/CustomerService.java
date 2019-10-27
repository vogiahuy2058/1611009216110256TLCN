package com.springboot.angular.coffeesystem.service.customer;

import com.springboot.angular.coffeesystem.dto.CustomerRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface CustomerService {
    ResponseDto createCustomer(CustomerRequestDto customerRequestDto);
    ResponseDto getAllCustomer();
    ResponseDto deleteCustomer(Integer id);
    ResponseDto editCustomer(CustomerRequestDto customerRequestDto);
    ResponseDto getCustomerById(Integer id);
    PagingResponseDto getAllCustomerPaging(int page, int size, String sort, String sortColumn);
}
