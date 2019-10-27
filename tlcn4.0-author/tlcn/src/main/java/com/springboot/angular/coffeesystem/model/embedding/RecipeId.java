package com.springboot.angular.coffeesystem.model.embedding;

import com.springboot.angular.coffeesystem.model.Drink;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable

public class RecipeId implements Serializable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeId recipeId = (RecipeId) o;
        return materialId.equals(recipeId.materialId) &&
                drinkId.equals(recipeId.drinkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, drinkId);
    }
}
