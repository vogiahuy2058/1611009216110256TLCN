package coffeesystem.model;

import coffeesystem.model.embedding.MaterialPriceId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "material_price")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterialPrice extends Auditable<String>{
    @EmbeddedId
    private MaterialPriceId materialPriceId;
    private float costPrice;
    private float priceFromSupplier;
    @ManyToOne
    @JoinColumn(name = "material_material_id")
    private Material material;
    private LocalDate lastDate;
    private boolean enable = true;
}
