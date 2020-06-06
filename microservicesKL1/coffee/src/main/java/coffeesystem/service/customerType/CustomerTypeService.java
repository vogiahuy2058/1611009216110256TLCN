package coffeesystem.service.customerType;

import coffeesystem.dto.CustomerTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CustomerTypeService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto createCustomerType(CustomerTypeDto customerTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllCustomerType();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto deleteCustomerType(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getCustomerTypeById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto editCustomerType(CustomerTypeDto customerTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNTANT', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllCustomerTypePaging(int page, int size, String sort, String sortColumn);
}
