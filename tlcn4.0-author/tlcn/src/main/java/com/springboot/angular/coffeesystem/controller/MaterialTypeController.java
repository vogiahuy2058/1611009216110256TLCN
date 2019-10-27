package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.MaterialTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.materialType.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/material-type")
public class MaterialTypeController {
    @Autowired
    MaterialTypeService materialTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMaterialType(@RequestBody MaterialTypeDto materialTypeDto){
        return ResponseEntity.ok(materialTypeService.createMaterialType(materialTypeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllMaterialTypePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.materialTypeService.getAllMaterialTypePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllMaterialType(){
        return  ResponseEntity.ok(this.materialTypeService.getAllMaterialType());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getMaterialTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(materialTypeService.getMaterialTypeById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteMaterialType(@RequestParam Integer id){
        return ResponseEntity.ok(materialTypeService.deleteMaterialType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editMaterialType(@RequestBody MaterialTypeDto materialTypeDto){
        return ResponseEntity.ok(materialTypeService.editMaterialType(materialTypeDto));
    }
}
