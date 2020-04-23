package com.springboot.angular.coffeesystem.service.invoiceDetail;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

import java.util.List;

public interface InvoiceDetailService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto deleteInvoiceDetail(Integer invoiceId, Integer drinkId, Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto createInvoiceDetail(InvoiceDetailRequestDto invoiceDetailRequestDto);
    //    ResponseDto deleteInvoiceDetail(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editInvoiceDetail(InvoiceDetailRequestDto invoiceDetailRequestDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getInvoiceDetailByInvoiceId(Integer invoiceId);
    ResponseDto getInvoiceDetailByInvoiceIdAndStatus(Integer invoiceId, Integer status);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getInvoiceDetailByID(Integer id);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editListInvoiceDetail(List<InvoiceDetailRequestDto> invoiceDetailRequestDtoList);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getMaxIdInvoiceDetail();
}
