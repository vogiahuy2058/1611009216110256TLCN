package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.CoffeeTableDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.coffeeTable.CoffeeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/table")
public class CoffeeTableController {
    @Autowired
    CoffeeTableService coffeeTableService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCoffeeTable(@RequestBody CoffeeTableDto coffeeTableDto){
        return ResponseEntity.ok(coffeeTableService.createCoffeeTable(coffeeTableDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllCoffeeTablePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.coffeeTableService.getAllCoffeeTablePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllCoffeeTable(){
        return  ResponseEntity.ok(this.coffeeTableService.getAllCoffeeTable());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getCoffeeTableById(@RequestParam Integer id){
        return ResponseEntity.ok(coffeeTableService.getCoffeeTableById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCoffeeTable(@RequestParam Integer id){
        return ResponseEntity.ok(coffeeTableService.deleteCoffeeTable(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editCoffeeTable(@RequestBody CoffeeTableDto coffeeTableDto){
        return ResponseEntity.ok(coffeeTableService.editCoffeeTable(coffeeTableDto));
    }
}
