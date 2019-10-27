package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllRolePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.roleService.getAllRolePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllRole(){
        return  ResponseEntity.ok(this.roleService.getAllRole());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getRoleById(@RequestParam Integer id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteRole(@RequestParam Integer id){
        return ResponseEntity.ok(roleService.deleteRole(id));
    }
}
