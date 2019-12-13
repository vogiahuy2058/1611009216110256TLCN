package com.springboot.angular.coffeesystem.service.invoiceDetail;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

import java.util.List;

public interface InvoiceDetailService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto deleteInvoiceDetail(Integer invoiceId, Integer drinkId, Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto createInvoiceDetail(InvoiceDetailRequestDto invoiceDetailRequestDto);
    //    ResponseDto deleteInvoiceDetail(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto editInvoiceDetail(InvoiceDetailRequestDto invoiceDetailRequestDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getInvoiceDetailByInvoiceId(Integer invoiceId);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getInvoiceDetailByID(Integer id);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto editListInvoiceDetail(List<InvoiceDetailRequestDto> invoiceDetailRequestDtoList);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getMaxIdInvoiceDetail();
}
