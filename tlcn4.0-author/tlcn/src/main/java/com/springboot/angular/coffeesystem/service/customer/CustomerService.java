package com.springboot.angular.coffeesystem.service.customer;

import com.springboot.angular.coffeesystem.dto.CustomerRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CustomerService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_CASHIER')")
    ResponseDto createCustomer(CustomerRequestDto customerRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_CASHIER', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllCustomer();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT')")
    ResponseDto deleteCustomer(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT')")
    ResponseDto editCustomer(CustomerRequestDto customerRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_CASHIER', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getCustomerById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_CASHIER', 'ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllCustomerPaging(int page, int size, String sort, String sortColumn);
}
