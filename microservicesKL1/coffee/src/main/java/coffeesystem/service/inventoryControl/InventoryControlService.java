package coffeesystem.service.inventoryControl;

import coffeesystem.dto.InventoryControlRequestDto;
import coffeesystem.dto.ResponseDto;

public interface InventoryControlService {
    ResponseDto createInventoryControl(InventoryControlRequestDto requestDto);
}