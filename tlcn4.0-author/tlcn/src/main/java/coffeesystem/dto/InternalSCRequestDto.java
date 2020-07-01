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
public class InternalSCRequestDto {
    private Integer id;
    private LocalDate date;
//    private float totalPrice;
    private String branchShop;
//    private String supplier;
    private Integer status;
    private LocalDate deliveryTime;
//    private LocalDate paymentTime;
}
