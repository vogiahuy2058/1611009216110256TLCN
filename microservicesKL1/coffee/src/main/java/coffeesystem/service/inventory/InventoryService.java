package coffeesystem.service.inventory;

import coffeesystem.dto.InventoryRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;

import java.time.LocalDate;

public interface InventoryService {
    ResponseDto createInventory(InventoryRequestDto inventoryRequestDto);
    ResponseDto getAllInventory();
    PagingResponseDto getAllInventoryPaging(int page, int size, String sort, String sortColumn);
    ResponseDto editInventory(InventoryRequestDto inventoryRequestDto);
    ResponseDto deleteInventory(Integer idMaterial, Integer idBranchShop, String firstDate);
    ResponseDto getByIdMaterialAndIdBranchShop(Integer materialId, Integer branchShopId);
    PagingResponseDto getAllByBranchShopIdPaging(int page, int size, String sort,
                                                 String sortColumn, Integer branchShopId);
    ResponseDto getByIdMaterialAndIdBranchShopAndFirstDate(Integer materialId, Integer branchShopId, String firstDate);
}
