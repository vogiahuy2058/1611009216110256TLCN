package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.MaterialPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.materialPrice.MaterialPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/material-price")
public class MaterialPriceController {
    @Autowired
    MaterialPriceService materialPriceService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMaterialPrice(@RequestBody MaterialPriceRequestDto materialPriceRequestDto){
        return ResponseEntity.ok(materialPriceService.createPriceOfMaterial(materialPriceRequestDto));
    }
    @PutMapping("/change")
    public ResponseEntity<ResponseDto> changeMaterialPrice(@RequestBody MaterialPriceRequestDto materialPriceRequestDto){
        return ResponseEntity.ok(materialPriceService.changePriceOfMaterial(materialPriceRequestDto));
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getMaterialByMaterialId(@RequestParam Integer materialId){
        return ResponseEntity.ok(this.materialPriceService.getPriceOfMaterial(materialId));
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> geAllMaterialByMaterial(){
        return ResponseEntity.ok(this.materialPriceService.getAllPriceOfMaterial());
    }

    @GetMapping("/get-all-paging")
    public ResponseEntity<PagingResponseDto> getAllDrinkPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "material.name") String sortColumn){
        return ResponseEntity.ok(this.materialPriceService.getAllMaterialPricePaging(page, size, sort, sortColumn));
    }
}
