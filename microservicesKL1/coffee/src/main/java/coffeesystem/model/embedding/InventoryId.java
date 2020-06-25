package coffeesystem.model.embedding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class InventoryId  implements Serializable {
    @Column(name = "first_date")
    private LocalDate firstDate;
    @Column(name = "id_material")
    private Integer idMaterial;
    @Column(name = "id_branch_shop")
    private Integer idBranchShop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryId that = (InventoryId) o;
        return firstDate.equals(that.firstDate) &&
                idMaterial.equals(that.idMaterial) &&
                idBranchShop.equals(that.idBranchShop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstDate, idMaterial, idBranchShop);
    }
}
