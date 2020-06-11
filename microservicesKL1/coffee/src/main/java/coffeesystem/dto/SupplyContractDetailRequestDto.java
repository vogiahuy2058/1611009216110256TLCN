package coffeesystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplyContractDetailRequestDto {
    private Integer id;
    private Integer materialId;
    private Integer supplyContractId;

    private float unitPrice;
    private float amount;
    private LocalDate deliveryTime;
    private LocalDate paymentTime;

}
