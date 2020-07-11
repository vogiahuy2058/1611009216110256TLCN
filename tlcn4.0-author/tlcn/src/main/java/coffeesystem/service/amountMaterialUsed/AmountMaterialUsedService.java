package coffeesystem.service.amountMaterialUsed;

import coffeesystem.dto.ResponseDto;

public interface AmountMaterialUsedService {
    ResponseDto createAmountMaterialUsed(Integer idBranchShop, Integer idMaterial, String checkDate);
    void updateAmountMaterialUsed(Integer idBranchShop, Integer idMaterial,
                                             float newMinAmount, float newMaxAmount);
    ResponseDto getAmountMaterialUsedStatusActive(Integer idBranchShop, Integer idMaterial);
}
