package coffeesystem.dto;

import coffeesystem.model.BranchShop;
import coffeesystem.model.Material;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinMaxInventoryDto {
    private Integer id;
    private Integer materialId;
    private Integer branchShopId;
    private float minInventory;
    private float maxInventory;


}
