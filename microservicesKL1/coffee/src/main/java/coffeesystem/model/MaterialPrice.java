package coffeesystem.model;

import coffeesystem.model.embedding.MaterialPriceId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "material_price")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterialPrice extends Auditable<String>{
    @EmbeddedId
    private MaterialPriceId materialPriceId;
    private float price;
    @ManyToOne
    @JoinColumn(name = "material_material_id")
    private Material material;
    private boolean enable = true;
}
