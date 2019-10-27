package com.springboot.angular.coffeesystem.service.invoiceDetail;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.InvoiceDetail;

public interface InvoiceDetailService {
    ResponseDto createInvoiceDetail(InvoiceDetailDto invoiceDetailDto);
//    ResponseDto deleteInvoiceDetail(Integer id);
    ResponseDto editInvoiceDetail(InvoiceDetailDto invoiceDetailDto);
}
