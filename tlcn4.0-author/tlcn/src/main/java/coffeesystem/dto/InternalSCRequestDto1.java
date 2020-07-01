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
public class InternalSCRequestDto1 {
    private String idkey;
    private Integer id;
    private LocalDate date;
    private String branchShop;
    private Integer status;
    private LocalDate deliveryTime;
}
