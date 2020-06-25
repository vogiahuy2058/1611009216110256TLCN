package coffeesystem.model;


import coffeesystem.model.embedding.InventoryId;
import coffeesystem.model.embedding.MinMaxInventoryId;
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
@Table(name = "inventory")
public class Inventory  extends Auditable<String>{
    @EmbeddedId
    private InventoryId inventoryId;
    private LocalDate lastDate;
    //ton dau ky
    private float backlogFirstDate;
    //so luong nhap trong ky
    private float importPeriod;
    //ton cuoi ky
    private float backlogLastDate=0;
    //so luong ban duoc
    private float quantitySold=0;
    private String status = "active";

    @ManyToOne
    @JoinColumn(name = "material_material_id")
    private Material material;
    @ManyToOne
    @JoinColumn(name = "bshop_bshop_id")
    private BranchShop branchShop;
    private boolean enable = true;
}
