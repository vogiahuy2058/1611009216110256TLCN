package coffeesystem.service.coffeeTable;


import coffeesystem.dto.CoffeeTableDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface CoffeeTableService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto createCoffeeTable(CoffeeTableDto coffeeTableDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto getAllCoffeeTable();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto deleteCoffeeTable(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto editCoffeeTable(CoffeeTableDto coffeeTableDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    ResponseDto getCoffeeTableById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllCoffeeTablePaging(int page, int size, String sort, String sortColumn);
}
