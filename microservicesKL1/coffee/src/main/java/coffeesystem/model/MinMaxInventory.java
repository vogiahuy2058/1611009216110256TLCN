package coffeesystem.model;

import coffeesystem.model.embedding.MaterialPriceId;
import coffeesystem.model.embedding.MinMaxInventoryId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "min_max_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MinMaxInventory extends Auditable<String>{
    @EmbeddedId
    private MinMaxInventoryId minMaxInventoryId;
    private float minInventory;
    private float maxInventory;
    @ManyToOne
    @JoinColumn(name = "material_material_id")
    private Material material;
    @ManyToOne
    @JoinColumn(name = "bshop_bshop_id")
    private BranchShop branchShop;
    private boolean enable = true;
}
