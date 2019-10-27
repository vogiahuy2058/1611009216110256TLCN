package com.springboot.angular.coffeesystem.service.account;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SignUpDto;

public interface AccountService {
    ResponseDto createAccount(SignUpDto signUpDto);
    ResponseDto deleteAccount(Integer id);
    ResponseDto getAccount(String username);
    ResponseDto getAllAccount();
    PagingResponseDto getAllAccountPaging(int page, int size, String sort, String sortColumn);
}
