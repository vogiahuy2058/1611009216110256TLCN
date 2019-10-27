package com.springboot.angular.coffeesystem.model;

import com.springboot.angular.coffeesystem.model.embedding.RecipeId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Recipe extends Auditable<String> {
    @EmbeddedId
    private RecipeId recipeId;
    @ManyToOne
    @MapsId("materialId")
    private Material material;

    @ManyToOne
    @MapsId("drinkId")
    private Drink drink;

    private boolean enable = true;
    private Integer amount;
    private String unit;


}
