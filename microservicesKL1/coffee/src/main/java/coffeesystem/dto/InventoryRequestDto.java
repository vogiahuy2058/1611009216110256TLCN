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
public class InventoryRequestDto {
    private Integer materialId;
    private Integer branchShopId;
    private LocalDate firstDate;
    //    private LocalDate lastDate;
//    private float backlogFirstDate;
    //so luong nhap trong ky
    private float importPeriod;
    //ton cuoi ky
    private float backlogLastDate;
    //so luong ban duoc
//    private float quantitySold;
}
