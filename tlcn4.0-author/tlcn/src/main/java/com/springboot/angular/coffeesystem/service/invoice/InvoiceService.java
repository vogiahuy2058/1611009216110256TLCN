package com.springboot.angular.coffeesystem.service.invoice;

import com.springboot.angular.coffeesystem.dto.InvoiceRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;

public interface InvoiceService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getAllInvoiceStatusTrue();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getAllInvoiceStatusFalse();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getInvoiceById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto editInvoice(InvoiceRequestDto invoiceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto deleteInvoice(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getAllInvoiceDateToDate(String fromDate, String toDate, Integer branchShopId);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getFullInvoiceById(Integer InvoiceId);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getMaxIdInvoice();

}
