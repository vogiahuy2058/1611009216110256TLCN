package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinMaxInventoryResponseDto {
    private Integer id;
    private Integer materialId;
    private Integer branchShopId;
    private float minInventory;
    private float maxInventory;
    private String materialName;
    private String branchShopName;
    private String unitName;
}
