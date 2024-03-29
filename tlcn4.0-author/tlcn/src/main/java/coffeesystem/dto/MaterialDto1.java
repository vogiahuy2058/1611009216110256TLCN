package coffeesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaterialDto1 {
    private Integer id;
    private String name;
    private float totalNumberOfRequest;
    private float totalQuantityAllow;

}
