package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.InvoiceRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceRequestDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllInvoicePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
//        return ResponseEntity.ok(this.invoiceService.getAllInvoicePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllInvoice(){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoice());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getInvoiceById(@RequestParam Integer id){

        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return ResponseEntity.ok(invoiceService.editInvoice(invoiceRequestDto));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteInvoice(@RequestParam Integer id){
        return ResponseEntity.ok(invoiceService.deleteInvoice(id));
    }
}
