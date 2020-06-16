package coffeesystem.model.embedding;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SupplyContractDetailId implements Serializable {
    @Column(name = "id")
    private Integer id;
    @Column(name = "material_id")
    private Integer materialId;
    @Column(name = "supply_contract_id")
    private Integer supplyContractId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        SupplyContractDetailId that = (SupplyContractDetailId) o;
        return materialId.equals(that.materialId) &&
                supplyContractId.equals(that.supplyContractId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, supplyContractId);
    }
}
