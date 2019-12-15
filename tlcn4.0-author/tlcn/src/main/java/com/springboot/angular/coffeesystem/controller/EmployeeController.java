package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.EmployeeRequestDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequestDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllEmployeePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.employeeService.getAllEmployeePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllEmployee(){
        return  ResponseEntity.ok(this.employeeService.getAllEmployee());
    }
    @GetMapping("/get-all-not-account")
    public ResponseEntity<ResponseDto> getEmployeeNotHaveAccountByEmployeeType(@RequestParam String nameEmployeeType){
        return  ResponseEntity.ok(
                this.employeeService.getEmployeeNotHaveAccountByEmployeeType(nameEmployeeType));
    }
    @GetMapping("/get-by-branch-shop")
    public ResponseEntity<ResponseDto> getEmployeeByBranchShopId(@RequestParam Integer branchShopId){
        return  ResponseEntity.ok(this.employeeService.getEmployeeByBranchShopId(branchShopId));
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getEmployeeById(@RequestParam Integer id){

        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @GetMapping("/get-username")
    public ResponseEntity<ResponseDto> getEmployeeByUsername(){

        return ResponseEntity.ok(employeeService.getEmployeeByUsername());
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestParam Integer id){
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.editEmployee(employeeRequestDto));
    }
    @PutMapping("/edit-not-employee-type")
    public ResponseEntity<ResponseDto> editEmployeeNotIncludeEmployeeType(@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.editEmployeeNotIncludeEmployeeType(employeeRequestDto));
    }
}
