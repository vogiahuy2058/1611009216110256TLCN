package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InternalSCDetailResponseDto1 {
    private Integer id;
    private Integer serial;
    private Integer materialId;
    private Integer internalSCId;

    private float numberOfRequest;
    private float quantityAllowed;
    private float quantityReceived;
    private String materialName;
    private String unitName;
    private float price;
    private float totalPrice;
}
