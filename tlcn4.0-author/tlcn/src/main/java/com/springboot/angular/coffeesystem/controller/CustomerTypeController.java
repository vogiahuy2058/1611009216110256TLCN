package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.CustomerTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.customerType.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/customer-type")
public class CustomerTypeController {
    @Autowired
    CustomerTypeService customerTypeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomerType(@RequestBody CustomerTypeDto customerTypeDto){
        return ResponseEntity.ok(customerTypeService.createCustomerType(customerTypeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllCustomerTypePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.customerTypeService.getAllCustomerTypePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllCustomerType(){
        return  ResponseEntity.ok(this.customerTypeService.getAllCustomerType());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getCustomerTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(customerTypeService.getCustomerTypeById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomerType(@RequestParam Integer id){
        return ResponseEntity.ok(customerTypeService.deleteCustomerType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editCustomerType(@RequestBody CustomerTypeDto customerTypeDto){
        return ResponseEntity.ok(customerTypeService.editCustomerType(customerTypeDto));
    }
}
