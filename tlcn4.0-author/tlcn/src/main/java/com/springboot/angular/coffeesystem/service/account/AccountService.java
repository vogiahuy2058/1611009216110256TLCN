package com.springboot.angular.coffeesystem.service.account;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SignUpDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface AccountService {
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto createAccount(SignUpDto signUpDto);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto deleteAccount(Integer id);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getAccount(String username);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getAllAccount();
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    PagingResponseDto getAllAccountPaging(int page, int size, String sort, String sortColumn);
}
