package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.InvoiceRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

//    @GetMapping("/get-all-true")
//    public ResponseEntity<PagingResponseDto> getAllInvoiceStatusTruePaging(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
//        return  ResponseEntity.ok(this.invoiceService.getAllInvoicePagingStatusTrue(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all-true-paging")
    public ResponseEntity<PagingResponseDto> getAllInvoiceStatusTrue(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus2Paging(page, size, sort, sortColumn));
    }

    @GetMapping("/get-all-true")
    public ResponseEntity<ResponseDto> getAllInvoiceStatusTrue(){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus2());
    }
    @GetMapping("/get-all-date-to-date-paging")
    public ResponseEntity<PagingResponseDto> getAllInvoiceDateToDatePaging(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                               @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                                                               @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn,
                                                               @RequestParam String fromDate,
                                                               @RequestParam String toDate,
                                                               @RequestParam Integer branchShopId){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceByFilterPaging(page, size, sort, sortColumn, fromDate, toDate, branchShopId));
    }
    @GetMapping("/get-all-date-to-date")
    public ResponseEntity<ResponseDto> getAllInvoiceDateToDate(@RequestParam String fromDate,
                                                               @RequestParam String toDate,
                                                               @RequestParam Integer branchShopId){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceDateToDate(fromDate, toDate, branchShopId));
    }

    @GetMapping("/get-all-false")
    public ResponseEntity<ResponseDto> getAllInvoiceStatusFalse(){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus0());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getInvoiceById(@RequestParam Integer id){

        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @GetMapping("/get-full-invoice")
    public ResponseEntity<ResponseDto> getFullInvoiceById(@RequestParam Integer invoiceId){

        return ResponseEntity.ok(invoiceService.getFullInvoiceById(invoiceId));
    }

    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInvoiceId(){

        return ResponseEntity.ok(invoiceService.getMaxIdInvoice());
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return ResponseEntity.ok(invoiceService.editInvoice(invoiceRequestDto));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteInvoice(@RequestParam Integer id){
        return ResponseEntity.ok(invoiceService.deleteInvoice(id));
    }

    @PutMapping("/delete-status-false")
    public ResponseEntity<ResponseDto> deleteInvoiceStatusFalse(){
        return ResponseEntity.ok(invoiceService.deleteInvoiceStatus0());
    }
}
