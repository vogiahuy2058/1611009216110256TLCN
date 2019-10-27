package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.TableTypeDto;
import com.springboot.angular.coffeesystem.service.tableType.TableTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/table-type")
public class TableTypeController {

    @Autowired
    TableTypeService tableTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTableType(@RequestBody TableTypeDto tableTypeDto){
        return ResponseEntity.ok(tableTypeService.createTableType(tableTypeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllTableTypePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.tableTypeService.getAllTableTypePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllTableType(){
        return  ResponseEntity.ok(this.tableTypeService.getAllTableType());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getTableTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(tableTypeService.getTableTypeById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteTableType(@RequestParam Integer id){
        return ResponseEntity.ok(tableTypeService.deleteTableType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editTableType(@RequestBody TableTypeDto tableTypeDto){
        return ResponseEntity.ok(tableTypeService.editTableType(tableTypeDto));
    }
}
