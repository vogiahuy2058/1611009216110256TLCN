package coffeesystem.service.orderType;

import coffeesystem.dto.OrderTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface OrderTypeService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createOrderType(OrderTypeDto orderTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllOrderType();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto deleteOrderType(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editOrderType(OrderTypeDto orderTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getOrderTypeById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllOrderTypePaging(int page, int size, String sort, String sortColumn);
}
