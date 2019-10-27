package com.springboot.angular.coffeesystem.service.invoice;

import com.springboot.angular.coffeesystem.dto.InvoiceRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface InvoiceService {
    ResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);
    ResponseDto getAllInvoice();
    ResponseDto getInvoiceById(Integer id);
    ResponseDto editInvoice(InvoiceRequestDto invoiceRequestDto);
    ResponseDto deleteInvoice(Integer id);
    PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn);
}
