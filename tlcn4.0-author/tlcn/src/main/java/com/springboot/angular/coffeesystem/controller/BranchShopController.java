package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.BranchShopDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.branchShop.BranchShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/branch-shop")
public class BranchShopController {
    @Autowired
    BranchShopService branchShopService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createBranchShop(@RequestBody BranchShopDto branchShopDto){
        return ResponseEntity.ok(branchShopService.createBranchShop(branchShopDto));
    }

//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllBranchPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.branchShopService.getAllBranchShopPaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllBranchShop(){
        return  ResponseEntity.ok(this.branchShopService.getAllBranchShop());
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getBranchShopById(@RequestParam Integer id){
        return ResponseEntity.ok(branchShopService.getBranchShopById(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteBranchShop(@RequestParam Integer id){
        return ResponseEntity.ok(branchShopService.deleteBranchShop(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editBranchShop(@RequestBody BranchShopDto branchShopDto){
        return ResponseEntity.ok(branchShopService.editBranchShop(branchShopDto));
    }
}
