package com.springboot.angular.coffeesystem.service.supplyContractDetail;

import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDetailDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface SupplyContractDetailService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createSupplyContractDetail(SupplyContractDetailDto supplyContractDetailDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto editSupplyContractDetail(SupplyContractDetailDto supplyContractDetailDto);
}
