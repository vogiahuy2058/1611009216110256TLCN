package com.springboot.angular.coffeesystem.service.invoice;

import com.springboot.angular.coffeesystem.dto.InvoiceRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface InvoiceService {
    ResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getAllInvoiceStatusTrue();
    ResponseDto getAllInvoiceStatusFalse();
    ResponseDto getInvoiceById(Integer id);
    ResponseDto editInvoice(InvoiceRequestDto invoiceRequestDto);
    ResponseDto deleteInvoice(Integer id);
    PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn);
}
