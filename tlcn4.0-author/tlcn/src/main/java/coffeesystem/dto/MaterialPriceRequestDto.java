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
public class MaterialPriceRequestDto {
    private Integer id;
    private Integer materialId;
    private LocalDate firstDate;
//    private LocalDate lastDate;
    private float costPrice;
    private float priceFromSupplier;
}
