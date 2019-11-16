package com.springboot.angular.coffeesystem.service.branchShop;

import com.springboot.angular.coffeesystem.dto.BranchShopDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BranchShopService {

//    @PreAuthorize("hasAnyRole('ROLE_BRANCH_MANAGER','ROLE_ACCOUNTANT', 'ROLE_ADMIN')")
    ResponseDto createBranchShop(BranchShopDto branchShopDto);
//    @PreAuthorize("hasAnyRole('ROLE_BRANCH_MANAGER','ROLE_ACCOUNTANT', 'ROLE_ADMIN', 'ROLE_CASHIER', 'ROLE_HR')")
    PagingResponseDto getAllBranchShopPaging(int page, int size, String sort, String sortColumn);
//    @PreAuthorize("hasAnyRole('ROLE_BRANCH_MANAGER','ROLE_ACCOUNTANT', 'ROLE_ADMIN', 'ROLE_CASHIER', 'ROLE_HR')")
    ResponseDto getAllBranchShop();
//    @PreAuthorize("hasAnyRole('ROLE_BRANCH_MANAGER','ROLE_ACCOUNTANT', 'ROLE_ADMIN')")
    ResponseDto deleteBranchShop(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_BRANCH_MANAGER','ROLE_ACCOUNTANT', 'ROLE_ADMIN')")
    ResponseDto editBranchShop(BranchShopDto branchShopDto);
//    @PreAuthorize("hasAnyRole('ROLE_BRANCH_MANAGER','ROLE_ACCOUNTANT', 'ROLE_ADMIN', 'ROLE_CASHIER', 'ROLE_HR')")
    ResponseDto getBranchShopById(Integer id);
}
