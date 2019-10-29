package com.springboot.angular.coffeesystem.service.supplyContract;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractRequestDto;

public interface SupplyContractService {
    ResponseDto createSupplyContract(SupplyContractRequestDto supplyContractRequestDto);
    ResponseDto getAllSupplyContract();
    ResponseDto deleteSupplyContract(Integer id);
    ResponseDto editSupplyContract(SupplyContractRequestDto supplyContractRequestDto);
    ResponseDto getSupplyContractById(Integer id);
    PagingResponseDto getAllSupplyContractPaging(int page, int size, String sort, String sortColumn);
}
