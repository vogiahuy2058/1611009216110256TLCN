package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.DrinkTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.drinkType.DrinkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/drink-type")
public class DrinkTypeController {
    @Autowired
    DrinkTypeService drinkTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDrinkType(@RequestBody DrinkTypeDto drinkTypeDto){
        return ResponseEntity.ok(drinkTypeService.createDrinkType(drinkTypeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllDrinkPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.drinkTypeService.getAllDrinkTypePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllDrinkType(){
        return  ResponseEntity.ok(this.drinkTypeService.getAllDrinkType());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getDrinkTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(drinkTypeService.getDrinkTypeById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDrinkType(@RequestParam Integer id){
        return ResponseEntity.ok(drinkTypeService.deleteDrinkType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editDrinkType(@RequestBody DrinkTypeDto drinkTypeDto){
        return ResponseEntity.ok(drinkTypeService.editDrinkType(drinkTypeDto));
    }
}
