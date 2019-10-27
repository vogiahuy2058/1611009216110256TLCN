package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SignUpDto;
import com.springboot.angular.coffeesystem.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(this.accountService.createAccount(signUpDto));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam Integer id){
        return ResponseEntity.ok(this.accountService.deleteAccount(id));
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getAccount(@RequestParam String username){
        return  ResponseEntity.ok(this.accountService.getAccount(username));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllAccountPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "username") String sortColumn){
//        return ResponseEntity.ok(this.accountService.getAllAccountPaging(page, size, sort, sortColumn));
//    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllAccount(){
        return  ResponseEntity.ok(this.accountService.getAllAccount());
    }

}
