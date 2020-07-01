package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InternalSCDetailRequestDto {
    private Integer id;
    private Integer materialId;
    private Integer internalSCId;

//    private float unitPrice;
    private float numberOfRequest;
    private float quantityAllowed;
    private float quantityReceived;
    //    private LocalDate deliveryTime;
//    private LocalDate paymentTime;
    private String unitName;
}
