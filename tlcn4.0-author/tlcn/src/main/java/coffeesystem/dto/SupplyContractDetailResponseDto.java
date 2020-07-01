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
public class SupplyContractDetailResponseDto {

    private Integer id;
    private Integer serial;
    private Integer materialId;
    private Integer supplyContractId;

    private float unitPrice;
    private float numberOfRequest;
    private float quantityReceived;
//    private LocalDate deliveryTime;
//    private LocalDate paymentTime;
    private String materialName;
    private String unitName;
}
