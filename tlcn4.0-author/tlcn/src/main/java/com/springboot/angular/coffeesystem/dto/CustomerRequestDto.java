package com.springboot.angular.coffeesystem.dto;

import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.CustomerType;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.util.IsExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
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
    @IsExists
    @Email(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;
    private String note;
    private float totalPurchase;

    private String customerType;

}
