package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.CustomerRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok(customerService.createCustomer(customerRequestDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllCustomerPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.customerService.getAllCustomerPaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllCustomer(){
        return  ResponseEntity.ok(this.customerService.getAllCustomer());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getCustomerById(@RequestParam Integer id){

        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam Integer id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok(customerService.editCustomer(customerRequestDto));
    }
}
