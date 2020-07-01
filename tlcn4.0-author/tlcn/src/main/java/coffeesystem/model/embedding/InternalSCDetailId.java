package coffeesystem.model.embedding;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class InternalSCDetailId implements Serializable {
    @Column(name = "id")
    private Integer id;
    @Column(name = "material_id")
    private Integer materialId;
    @Column(name = "internal_sc_id")
    private Integer internalSCId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalSCDetailId that = (InternalSCDetailId) o;
        return id.equals(that.id) &&
                materialId.equals(that.materialId) &&
                internalSCId.equals(that.internalSCId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialId, internalSCId);
    }
}
