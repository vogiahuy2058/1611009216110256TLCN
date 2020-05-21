package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.UnitDto;
import com.springboot.angular.coffeesystem.service.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/unit")
public class UnitController {
    @Autowired
    UnitService unitService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUnit(@RequestBody UnitDto unitDto){
        return ResponseEntity.ok(unitService.createUnit(unitDto));
    }
    @GetMapping("/get-all-paging")
    public ResponseEntity<PagingResponseDto> getAllUnitPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.unitService.getAllUnitPaging(page, size, sort, sortColumn));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllUnit(){
        return  ResponseEntity.ok(this.unitService.getAllUnit());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getUnitById(@RequestParam Integer id){
        return ResponseEntity.ok(unitService.getUnitById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUnit(@RequestParam Integer id){
        return ResponseEntity.ok(unitService.deleteUnit(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editUnit(@RequestBody UnitDto unitDto){
        return ResponseEntity.ok(unitService.editUnit(unitDto));
    }
}
