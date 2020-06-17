package coffeesystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponseDto {
    private Integer id;
    private String name;
    private String email;
    private String branchShop;
    private String employeeType;
    private String idCuaHuy;
}
