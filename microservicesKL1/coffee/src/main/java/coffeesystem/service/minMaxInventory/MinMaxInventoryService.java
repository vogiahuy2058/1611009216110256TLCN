package coffeesystem.service.minMaxInventory;

import coffeesystem.dto.MinMaxInventoryRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;

public interface MinMaxInventoryService {
    ResponseDto createMinMaxInventory(MinMaxInventoryRequestDto minMaxInventoryRequestDto);
    ResponseDto editMinMaxInventory(MinMaxInventoryRequestDto minMaxInventoryRequestDto);
    ResponseDto deleteMinMaxInventory(Integer materialId, Integer branchShopId);
    ResponseDto getAllMinMaxInventory();
    PagingResponseDto getAllMinMaxInventoryPaging(int page, int size, String sort, String sortColumn);
    ResponseDto getMinMaxByIdMaterialAndIdBranchShop(Integer materialId, Integer branchShopId);
    PagingResponseDto getAllByBranchShopIdPaging(int page, int size, String sort,
                                                 String sortColumn, Integer branchShoId);
}
