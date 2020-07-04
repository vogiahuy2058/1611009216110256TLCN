package coffeesystem.service.InternalSC;

import coffeesystem.dto.InternalSCRequestDto;
import coffeesystem.dto.InternalSCRequestDto1;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;

import java.util.List;

public interface InternalSCService {
    //chua sua phan quyen
    ResponseDto createInternalSC(InternalSCRequestDto internalSCRequestDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllInternalSC();
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto deleteInternalSC(Integer id);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto editInternalSC(InternalSCRequestDto internalSCRequestDto);
    ResponseDto editListInternalSC(List<InternalSCRequestDto1> internalSCRequestDto1List);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getInternalSCById(Integer id);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllInternalSCPaging(int page, int size, String sort, String sortColumn);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllInternalSCDateToDate(String fromDate, String toDate);
    ResponseDto getMaxIdInternalSC();
    ResponseDto getInternalSCHaveMaxIdByIdBranchShopAndStatus(Integer idBranchShop, Integer status);
    ResponseDto getTotalNumberOfRequestAndTotalQuantityAllowMaterial(List<InternalSCRequestDto1> internalSCRequestDto1s);
    ResponseDto getBranchShopExistInInternalSCStatus1DateLessThanNow();
    ResponseDto getAllInternalSCByBranchShopAndStatus(Integer idBranchShop, Integer status);
    ResponseDto getTotalMaterialInInternalSCStatus1DateLessThanNowByIdBranchShop(Integer idBranchShop);
    ResponseDto createNewInternalSCStatus1DateLessThanNow();
    ResponseDto createNewInternalSCStatus8To3DateLessThanNow();
}
