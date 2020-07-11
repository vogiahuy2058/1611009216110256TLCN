package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeResponseDto {
    private Integer id;
    private Integer materialId;
    private String materialName;
    private Integer drinkId;
    private String drinkName;
    private float minAmount;
    private float maxAmount;
    private String unitName;
}
