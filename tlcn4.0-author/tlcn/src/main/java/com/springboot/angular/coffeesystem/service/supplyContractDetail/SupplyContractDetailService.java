package com.springboot.angular.coffeesystem.service.supplyContractDetail;

import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDetailDto;

public interface SupplyContractDetailService {
    ResponseDto createSupplyContractDetail(SupplyContractDetailDto supplyContractDetailDto);
    ResponseDto editSupplyContractDetail(SupplyContractDetailDto supplyContractDetailDto);
}
