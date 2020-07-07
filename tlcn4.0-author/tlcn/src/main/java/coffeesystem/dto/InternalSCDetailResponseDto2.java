package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InternalSCDetailResponseDto2 {
    private Integer serial;
    private Integer id;
    private String materialName;
    private float quantityAllowed;
    private String unitName;

}
