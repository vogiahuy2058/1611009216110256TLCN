package coffeesystem.service.employeeType;

import coffeesystem.dto.EmployeeTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface EmployeeTypeService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto createEmployeeType(EmployeeTypeDto employeeTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto getAllEmployeeType();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto deleteEmployeeType(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto editEmployeeType(EmployeeTypeDto employeeTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto getEmployeeTypeById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    PagingResponseDto getAllEmployeeTypePaging(int page, int size, String sort, String sortColumn);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HR')")
    ResponseDto getMaxIdEmployeeType();
}
