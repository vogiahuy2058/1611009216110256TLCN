package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.OrderTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.orderType.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/order-type")
public class OrderTypeController {
    @Autowired
    OrderTypeService orderTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrderType(@RequestBody OrderTypeDto orderTypeDto){
        return ResponseEntity.ok(orderTypeService.createOrderType(orderTypeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllOrderTypePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.orderTypeService.getAllOrderTypePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllOrderType(){
        return  ResponseEntity.ok(this.orderTypeService.getAllOrderType());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getOrderTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(orderTypeService.getOrderTypeById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteOrderType(@RequestParam Integer id){
        return ResponseEntity.ok(orderTypeService.deleteOrderType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editOrderType(@RequestBody OrderTypeDto orderTypeDto){
        return ResponseEntity.ok(orderTypeService.editOrderType(orderTypeDto));
    }
}
