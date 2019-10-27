package com.springboot.angular.coffeesystem.controller;


import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplierDto;
import com.springboot.angular.coffeesystem.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplier(@RequestBody SupplierDto supplierDto){
        return ResponseEntity.ok(supplierService.createSupplier(supplierDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllSupplierPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.supplierService.getAllSupplierPaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllSupplier(){
        return  ResponseEntity.ok(this.supplierService.getAllSupplier());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getSupplierById(@RequestParam Integer id){
        return ResponseEntity.ok(supplierService.getSuppierById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSupplier(@RequestParam Integer id){
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplier(@RequestBody SupplierDto supplierDto){
        return ResponseEntity.ok(supplierService.editSupplier(supplierDto));
    }
}
