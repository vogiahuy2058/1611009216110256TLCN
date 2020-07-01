package coffeesystem.service.supplyContractDetail;


import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplyContractDetailRequestDto;

import java.util.List;

public interface SupplyContractDetailService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto createSupplyContractDetail(SupplyContractDetailRequestDto supplyContractDetailRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    ResponseDto editSupplyContractDetail(SupplyContractDetailRequestDto supplyContractDetailRequestDto);
    ResponseDto editListSupplyContractDetail(List<SupplyContractDetailRequestDto> supplyContractDetailRequestDtoList);
    ResponseDto deleteSupplyContractDetail(Integer supplyContractId, Integer materialId, Integer id);
    ResponseDto getSupplyContractDetailBySupplyContractId(Integer supplyContractId);
    PagingResponseDto getSupplyContractDetailBySupplyContractIdPaging(
            int page, int size, String sort, String sortColumn, Integer supplyContractId);
    ResponseDto getSupplyContractDetailBySupplyContractIdAndStatus(Integer supplyContractId, Integer status);
    ResponseDto getSupplyContractDetailByID(Integer id);
    ResponseDto getMaxIdSupplyContractDetail();
}
