package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceResponseDto {
    private Integer id;
    private String date;
    private String customerPhone;
    private String customerName;
    private String branchShop;
    private String cashierName;
    private String orderType;
    private Integer numberPosition;
    private float vat;
    private float totalDiscount;
    private float totalPrice;
    private float realPay;
    private Integer status;
//    private String coffeeTable;

}
