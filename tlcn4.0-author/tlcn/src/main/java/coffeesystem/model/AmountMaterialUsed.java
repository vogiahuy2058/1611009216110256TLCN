package coffeesystem.model;

import coffeesystem.model.embedding.AmountMaterialUsedId;
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
@Table(name = "amount_material_used")
public class AmountMaterialUsed {
    @EmbeddedId
    private AmountMaterialUsedId amountMaterialUsedId;
    private float totalMinAmount;
    private float totalMaxAmount;
    private float totalAverageAmount;
    private String status = "active";
    @ManyToOne
    @MapsId("idMaterial")
    private Material material;

    @ManyToOne
    @MapsId("idBranchShop")
    private BranchShop branchShop;
}
