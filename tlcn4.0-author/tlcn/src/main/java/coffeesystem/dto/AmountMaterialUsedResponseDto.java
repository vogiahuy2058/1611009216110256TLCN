package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AmountMaterialUsedResponseDto {
    private Integer id;
    private String checkDate;
    private Integer idMaterial;
    private Integer idBranchShop;
    private float totalMinAmount;
    private float totalMaxAmount;
    private float totalAverageAmount;

}
