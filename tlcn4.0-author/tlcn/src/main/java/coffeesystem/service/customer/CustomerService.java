package coffeesystem.service.customer;


import coffeesystem.dto.CustomerRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_CASHIER')")
    ResponseDto createCustomer(CustomerRequestDto customerRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_CASHIER', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllCustomer();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
    ResponseDto deleteCustomer(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
    ResponseDto editCustomer(CustomerRequestDto customerRequestDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
    ResponseDto editListCustomer(List<CustomerRequestDto> customerRequestDtoList);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_CASHIER', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getCustomerById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_CASHIER', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllCustomerPaging(int page, int size, String sort, String sortColumn);
    ResponseDto getCustomerByPhone(String phone);
}
