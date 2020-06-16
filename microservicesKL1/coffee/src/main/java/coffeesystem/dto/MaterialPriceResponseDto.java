package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialPriceResponseDto {
    private Integer id;
    private Integer materialId;
    private String materialName;
    private String firstDate;
    private String lastDate;
    private float costPrice;
    private float priceFromSupplier;
}
