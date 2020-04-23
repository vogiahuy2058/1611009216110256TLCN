package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.invoiceDetail.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/invoice-detail")
public class InvoiceDetailController {
    @Autowired
    InvoiceDetailService invoiceDetailService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInvoiceDetail(@RequestBody InvoiceDetailRequestDto invoiceDetailRequestDto){
        return ResponseEntity.ok(invoiceDetailService.createInvoiceDetail(invoiceDetailRequestDto));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInvoiceDetail(@RequestBody InvoiceDetailRequestDto invoiceDetailRequestDto){
        return ResponseEntity.ok(invoiceDetailService.editInvoiceDetail(invoiceDetailRequestDto));
    }

    @PutMapping("/edit-list")
    public ResponseEntity<ResponseDto> editListInvoiceDetail(@RequestBody List<InvoiceDetailRequestDto> invoiceDetailRequestDtoList){
        return ResponseEntity.ok(invoiceDetailService.editListInvoiceDetail(invoiceDetailRequestDtoList));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteInvoiceDetail(@RequestParam Integer invoiceId,
                                                           @RequestParam Integer drinkId,
                                                           @RequestParam Integer id){
        return ResponseEntity.ok(invoiceDetailService.deleteInvoiceDetail(invoiceId, drinkId, id));
    }
    @GetMapping("/get-by-id-invoice")
    public ResponseEntity<ResponseDto> getInvoiceDetailByInvoiceId(@RequestParam Integer invoiceId){

        return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailByInvoiceId(invoiceId));
    }
    @GetMapping("/get-by-id-status-invoice")
    public ResponseEntity<ResponseDto> getInvoiceDetailByInvoiceIdAndStatusInvoice
            (@RequestParam Integer invoiceId,
             @RequestParam Integer status){

        return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailByInvoiceIdAndStatus(invoiceId, status));
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<ResponseDto> getInvoiceDetailById(@RequestParam Integer id){

        return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailByID(id));
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInvoiceDetailId(){

        return ResponseEntity.ok(invoiceDetailService.getMaxIdInvoiceDetail());
    }
}
