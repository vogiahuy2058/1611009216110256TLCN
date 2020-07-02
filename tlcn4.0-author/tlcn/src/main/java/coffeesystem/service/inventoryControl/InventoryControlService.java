package coffeesystem.service.inventoryControl;

import coffeesystem.dto.InventoryControlRequestDto;
import coffeesystem.dto.ResponseDto;

public interface InventoryControlService {
    ResponseDto createInventoryControl(InventoryControlRequestDto requestDto);
    ResponseDto updateInventoryControl(InventoryControlRequestDto requestDto);
    ResponseDto getAllInventoryControlStatusActive();
    ResponseDto getAllInventoryControlByIdBranchShopStatusActive(Integer idBranchShop);
    ResponseDto getAllInventoryControl();
    ResponseDto getInventoryControlByIdMaterialIdBranchShopStatusActive(Integer idMaterial,Integer idBranchShop);
    ResponseDto getMaterialExistInInventoryControlByIdBranchShop(Integer branchShopId);
    ResponseDto editStatusInventoryControl(Integer idMaterial,
                                           Integer idBranchShop, String firstDate, String status);
    ResponseDto deleteInventoryControl(Integer idMaterial, Integer idBranchShop, String firstDate);
}
