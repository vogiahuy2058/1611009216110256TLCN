package com.springboot.angular.coffeesystem.service.customerType;

import com.springboot.angular.coffeesystem.dto.CustomerTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CustomerTypeService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto createCustomerType(CustomerTypeDto customerTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllCustomerType();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto deleteCustomerType(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getCustomerTypeById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto editCustomerType(CustomerTypeDto customerTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllCustomerTypePaging(int page, int size, String sort, String sortColumn);
}
