package com.springboot.angular.coffeesystem.model.embedding;

import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.Supplier;
import com.springboot.angular.coffeesystem.model.SupplyContract;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SupplyContractId implements Serializable {
    @Column(name = "material_id")
    private Integer materialId;
    @Column(name = "supply_contract_id")
    private Integer supplyContractId;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getSupplyContractId() {
        return supplyContractId;
    }

    public void setSupplyContractId(Integer supplyContractId) {
        this.supplyContractId = supplyContractId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyContractId that = (SupplyContractId) o;
        return materialId.equals(that.materialId) &&
                supplyContractId.equals(that.supplyContractId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, supplyContractId);
    }
}
