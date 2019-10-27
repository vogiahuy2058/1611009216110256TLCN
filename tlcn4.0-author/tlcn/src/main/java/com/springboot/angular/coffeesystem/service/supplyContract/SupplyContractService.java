package com.springboot.angular.coffeesystem.service.supplyContract;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDto;
import com.springboot.angular.coffeesystem.model.SupplyContract;

public interface SupplyContractService {
    ResponseDto createSupplyContract(SupplyContractDto supplyContractDto);
    ResponseDto getAllSupplyContract();
    ResponseDto deleteSupplyContract(Integer id);
    ResponseDto editSupplyContract(SupplyContractDto supplyContractDto);
    ResponseDto getSupplyContractById(Integer id);
    PagingResponseDto getAllSupplyContractPaging(int page, int size, String sort, String sortColumn);
}
