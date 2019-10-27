package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDto;
import com.springboot.angular.coffeesystem.service.supplyContract.SupplyContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/supply-contract")
public class SupplyContractController {
    @Autowired
    SupplyContractService supplyContractService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplyContract(@RequestBody SupplyContractDto supplyContractDto){
        return ResponseEntity.ok(supplyContractService.createSupplyContract(supplyContractDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllSupplyContractPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
//        return ResponseEntity.ok(this.supplyContractService.getAllSupplyContractPaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllSupplyContract(){
        return  ResponseEntity.ok(this.supplyContractService.getAllSupplyContract());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getSupplyContractId(@RequestParam Integer id){
        return ResponseEntity.ok(supplyContractService.getSupplyContractById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSupplyContract(@RequestParam Integer id){
        return ResponseEntity.ok(supplyContractService.deleteSupplyContract(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplyContract(@RequestBody SupplyContractDto supplyContractDto){
        return ResponseEntity.ok(supplyContractService.editSupplyContract(supplyContractDto));
    }
}
