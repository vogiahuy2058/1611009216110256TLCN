package com.springboot.angular.coffeesystem.controller;


import com.springboot.angular.coffeesystem.dto.MaterialDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/material")
public class MaterialController {
    @Autowired
    MaterialService materialService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMaterial(@RequestBody MaterialDto materialDto){
        return ResponseEntity.ok(materialService.createMaterial(materialDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllMaterialPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.materialService.getAllMaterialPaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllMaterial(){
        return  ResponseEntity.ok(this.materialService.getAllMaterial());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getMaterialById(@RequestParam Integer id){
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteMaterial(@RequestParam Integer id){
        return ResponseEntity.ok(materialService.deleteMaterial(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editMaterial(@RequestBody MaterialDto materialDto){
        return ResponseEntity.ok(materialService.editMaterial(materialDto));
    }
//    @PostMapping("change-price")
//    public ResponseEntity<ResponseDto> changePrice(@RequestParam Integer id,
//                                                   @RequestParam float newPrice){
//        return ResponseEntity.ok(materialService.changePrice(id,newPrice));
//    }
}
