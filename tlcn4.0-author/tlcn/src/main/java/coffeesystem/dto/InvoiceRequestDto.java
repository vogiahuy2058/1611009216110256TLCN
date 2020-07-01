package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceRequestDto {
    private Integer id;
    private float vat;
    private float realPay;
    private float totalPrice;
    private ZonedDateTime date;
    private float totalDiscount;
    private Integer numberPosition;
    private String customerPhone;
    private Integer status;

//    private String branchShop;

//    private String coffeeTable;

    private String orderType;

}
