package coffeesystem.model;

import coffeesystem.model.embedding.InventoryId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "inventory_control")
public class InventoryControl  extends Auditable<String>{
    @EmbeddedId
    private InventoryId inventoryId;
    private LocalDate checkDate;
    private float remainingAmount;
    private float virtualRemainingAmount;

    private String status = "active";

    @ManyToOne
    @JoinColumn(name = "material_material_id")
    private Material material;
    @ManyToOne
    @JoinColumn(name = "bshop_bshop_id")
    private BranchShop branchShop;
    private boolean enable = true;
}
