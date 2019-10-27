package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDetailDto;
import com.springboot.angular.coffeesystem.model.SupplyContractDetail;
import com.springboot.angular.coffeesystem.service.invoiceDetail.InvoiceDetailService;
import com.springboot.angular.coffeesystem.service.supplyContractDetail.SupplyContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/suppy-contract-detail")
public class SupplyContractDetailController {
    @Autowired
    SupplyContractDetailService detailService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplyContractDetail(@RequestBody SupplyContractDetailDto supplyContractDetailDto){
        return ResponseEntity.ok(detailService.createSupplyContractDetail(supplyContractDetailDto));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplyContractDetail(@RequestBody SupplyContractDetailDto supplyContractDetailDto){
        return ResponseEntity.ok(detailService.editSupplyContractDetail(supplyContractDetailDto));
    }
}
