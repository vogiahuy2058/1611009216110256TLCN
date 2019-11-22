package com.springboot.angular.coffeesystem.service.invoiceDetail;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.InvoiceDetail;
import org.springframework.security.access.prepost.PreAuthorize;

public interface InvoiceDetailService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto deleteInvoiceDetail(Integer invoiceId, Integer drinkId);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto createInvoiceDetail(InvoiceDetailDto invoiceDetailDto);
    //    ResponseDto deleteInvoiceDetail(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto editInvoiceDetail(InvoiceDetailDto invoiceDetailDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getInvoiceDetailByInvoiceId(Integer invoiceId);
}
