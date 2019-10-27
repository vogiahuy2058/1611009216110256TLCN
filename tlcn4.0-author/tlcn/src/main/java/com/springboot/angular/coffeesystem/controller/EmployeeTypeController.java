package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.EmployeeTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.employeeType.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee-type")
public class EmployeeTypeController {
    @Autowired
    EmployeeTypeService employeeTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployeeType(@RequestBody EmployeeTypeDto employeeTypeDto){
        return ResponseEntity.ok(employeeTypeService.createEmployeeType(employeeTypeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllEmployeeTypePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.employeeTypeService.getAllEmployeeTypePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllEmployeeType(){
        return  ResponseEntity.ok(this.employeeTypeService.getAllEmployeeType());
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getEmployeeTypeById(@RequestParam Integer id){
        return  ResponseEntity.ok(this.employeeTypeService.getEmployeeTypeById(id));
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployeeType(@RequestParam Integer id){
        return ResponseEntity.ok(employeeTypeService.deleteEmployeeType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editEmployeeType(@RequestBody EmployeeTypeDto employeeTypeDto){
        return ResponseEntity.ok(employeeTypeService.editEmployeeType(employeeTypeDto));
    }
}
