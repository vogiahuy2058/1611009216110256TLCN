package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialDto2 {
    private Integer id;
    private String name;
    private String unitName;
    private String materialType;
    private String remainingAmount;
}
