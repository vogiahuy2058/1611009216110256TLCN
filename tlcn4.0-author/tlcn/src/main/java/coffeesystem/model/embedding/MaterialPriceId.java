package coffeesystem.model.embedding;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class MaterialPriceId implements Serializable {
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_material")
    private Integer idMaterial;
    @Column(name = "first_date")
    private LocalDate firstDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialPriceId that = (MaterialPriceId) o;
        return id.equals(that.id) &&
                idMaterial.equals(that.idMaterial) &&
                firstDate.equals(that.firstDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMaterial, firstDate);
    }
}
