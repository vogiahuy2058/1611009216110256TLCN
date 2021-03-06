package coffeesystem.service.tableType;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.TableTypeDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TableTypeService {

    ResponseDto createTableTypeList(List<TableTypeDto> tableTypeDtoList);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BRANCH_MANAGER')")
    ResponseDto createTableType(TableTypeDto tableTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllTableType();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BRANCH_MANAGER')")
    ResponseDto deleteTableType(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BRANCH_MANAGER')")
    ResponseDto editTableType(TableTypeDto tableTypeDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getTableTypeById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER')")
    PagingResponseDto getAllTableTypePaging(int page, int size, String sort, String sortColumn);
}
