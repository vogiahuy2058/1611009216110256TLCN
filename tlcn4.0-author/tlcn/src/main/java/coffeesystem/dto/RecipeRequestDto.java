package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeRequestDto {
    private Integer id;
    private Integer materialId;
    private Integer drinkId;
    private float minAmount;
    private float maxAmount;




}
