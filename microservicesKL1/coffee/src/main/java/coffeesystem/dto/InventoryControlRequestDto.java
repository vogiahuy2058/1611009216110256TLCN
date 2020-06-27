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
public class InventoryControlRequestDto {
    private Integer id;
    private LocalDate firstDate;
    private Integer materialId;
    private Integer branchShopId;
    private LocalDate checkDate;
    private float remainingAmount;
}
