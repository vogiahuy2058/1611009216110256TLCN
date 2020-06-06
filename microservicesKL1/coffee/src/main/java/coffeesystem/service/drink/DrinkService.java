package coffeesystem.service.drink;

import coffeesystem.dto.DrinkDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DrinkService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    ResponseDto getAllDrinkByDrinkType(String name);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    ResponseDto getAllDrink();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER')")
    PagingResponseDto getAllDrinkPaging(int page, int size, String sort, String sortColumn);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getDrinkHavePriceByDrinkType(String nameDrinkType);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER')")
    ResponseDto getAllDrinkHavePrice();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_MANAGE_ALL_BRANCH', 'ROLE_BRANCH_MANAGER')")
    ResponseDto createDrink(DrinkDto drinkDTO);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_MANAGE_ALL_BRANCH', 'ROLE_BRANCH_MANAGER')")
    ResponseDto editDrink(DrinkDto drinkDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_MANAGE_ALL_BRANCH', 'ROLE_BRANCH_MANAGER')")
    ResponseDto deleteDrink(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getDrinkById(Integer id);
}
