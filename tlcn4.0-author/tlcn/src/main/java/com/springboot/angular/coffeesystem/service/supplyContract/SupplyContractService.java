package com.springboot.angular.coffeesystem.service.supplyContract;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractRequestDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface SupplyContractService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createSupplyContract(SupplyContractRequestDto supplyContractRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllSupplyContract();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto deleteSupplyContract(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto editSupplyContract(SupplyContractRequestDto supplyContractRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getSupplyContractById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllSupplyContractPaging(int page, int size, String sort, String sortColumn);
}
