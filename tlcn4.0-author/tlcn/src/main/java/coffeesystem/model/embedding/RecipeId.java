package coffeesystem.model.embedding;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable

public class RecipeId implements Serializable{
    @Column(name = "id")
    private Integer id;
    @Column(name = "material_id")
    private Integer materialId;
    @Column(name = "drink_id")
    private Integer drinkId;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeId recipeId = (RecipeId) o;
        return id.equals(recipeId.id) &&
                materialId.equals(recipeId.materialId) &&
                drinkId.equals(recipeId.drinkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, materialId, drinkId);
    }
}
