package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BranchShopStatisticsDto {
    private Integer id;
    private String name;
    private float totalPrice;
    private float realPay;

}
