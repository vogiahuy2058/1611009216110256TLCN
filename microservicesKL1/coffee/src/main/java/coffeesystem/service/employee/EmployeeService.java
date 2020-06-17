package coffeesystem.service.employee;


import coffeesystem.dto.EmployeeRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface EmployeeService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllEmployee();
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getEmployeeNotHaveAccountByEmployeeType(String name);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getEmployeeByBranchShopId(Integer branchShopId);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto deleteEmployee(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto editEmployee(EmployeeRequestDto employeeRequestDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')");
    ResponseDto editEmployeeNotIncludeEmployeeType(EmployeeRequestDto employeeRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getEmployeeById(Integer id);
    ResponseDto getEmployeeByUsername();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllEmployeePaging(int page, int size, String sort, String sortColumn);
    ResponseDto getMaxIdEmployee();
}
