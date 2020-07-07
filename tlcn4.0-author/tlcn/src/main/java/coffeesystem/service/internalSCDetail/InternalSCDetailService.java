package coffeesystem.service.internalSCDetail;

import coffeesystem.dto.InternalSCDetailRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplyContractDetailRequestDto;

import java.util.List;

public interface InternalSCDetailService {
    ResponseDto createInternalSCDetail(InternalSCDetailRequestDto internalSCDetailRequestDto);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto editInternalSCDetail(InternalSCDetailRequestDto internalSCDetailRequestDto);
    ResponseDto editListInternalSCDetail(List<InternalSCDetailRequestDto> internalSCDetailRequestDtoList);
    ResponseDto deleteInternalSCDetail(Integer internalSCId, Integer materialId, Integer id);
    ResponseDto deleteInternalSCDetail(Integer id);
    ResponseDto getInternalSCDetailByInternalSCId(Integer internalSCId);
    ResponseDto getInternalSCDetailByInternalSCIdFilter(Integer internalSCId);
    PagingResponseDto getInternalSCDetailByInternalSCPaging(
            int page, int size, String sort, String sortColumn, Integer internalSCId);
    ResponseDto getInternalSCDetailByInternalSCIdAndStatus(Integer internalSCId, Integer status);
    ResponseDto getInternalSCDetailByID(Integer id);
    ResponseDto getMaxIdInternalSCDetail();
}
