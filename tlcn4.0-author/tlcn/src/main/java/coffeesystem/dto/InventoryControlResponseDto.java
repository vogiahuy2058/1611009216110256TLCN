package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryControlResponseDto {
    private Integer id;
    private String firstDate;
    private Integer materialId;
    private Integer branchShopId;
    private String status;
    private String checkDate;
    private float remainingAmount;
    private float virtualRemainingAmount;
    private String materialName;
    private String branchShopName;
    private String unitName;
}
