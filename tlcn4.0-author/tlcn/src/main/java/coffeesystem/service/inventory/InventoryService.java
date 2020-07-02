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
    ResponseDto deleteInventory(Integer id);
    ResponseDto getByIdMaterialAndIdBranchShop(Integer materialId, Integer branchShopId);
    ResponseDto getByIdMaterialAndIdBranchShopStatusActive(Integer materialId, Integer branchShopId);
    PagingResponseDto getAllByBranchShopIdPaging(int page, int size, String sort,
                                                 String sortColumn, Integer branchShopId);
    ResponseDto getAllByBranchShopId(Integer branchShopId);
    PagingResponseDto getAllByBranchShopIdAndStatusPaging(int page, int size, String sort,
                                                          String sortColumn, Integer branchShopId, String status);
    ResponseDto getByIdMaterialAndIdBranchShopAndFirstDate(Integer materialId, Integer branchShopId, String firstDate);
    ResponseDto getMaterialExistInInventoryByIdBranchShop(Integer branchShopId);
}
