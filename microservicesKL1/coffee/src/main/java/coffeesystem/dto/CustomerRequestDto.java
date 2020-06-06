package coffeesystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequestDto {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private LocalDate birthDay;
    private boolean sex;
    private String email;
    private String note;
    private float totalPurchase;

    private String customerType;

}
