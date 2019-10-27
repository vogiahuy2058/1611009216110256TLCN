package com.springboot.angular.coffeesystem.service.branchShop;

import com.springboot.angular.coffeesystem.dto.BranchShopDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface BranchShopService {
    ResponseDto createBranchShop(BranchShopDto branchShopDto);
    PagingResponseDto getAllBranchShopPaging(int page, int size, String sort, String sortColumn);
    ResponseDto getAllBranchShop();
    ResponseDto deleteBranchShop(Integer id);
    ResponseDto editBranchShop(BranchShopDto branchShopDto);
    ResponseDto getBranchShopById(Integer id);
}
