package com.springboot.angular.coffeesystem.service.supplier;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplierDto;

public interface SupplierService {
    ResponseDto createSupplier(SupplierDto supplierDto);
    ResponseDto getAllSupplier();
    ResponseDto deleteSupplier(Integer id);
    ResponseDto editSupplier(SupplierDto supplierDto);
    ResponseDto getSuppierById(Integer id);
    PagingResponseDto getAllSupplierPaging(int page, int size, String sort, String sortColumn);
}
