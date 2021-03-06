package coffeesystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeTableDto {
    private Integer id;
    private String name;
    private Integer numberOfChair;
    private String note;

    private String tableType;

}
