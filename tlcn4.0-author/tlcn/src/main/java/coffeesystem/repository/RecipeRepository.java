package coffeesystem.repository;


import coffeesystem.model.Drink;
import coffeesystem.model.Material;
import coffeesystem.model.Recipe;
import coffeesystem.model.embedding.RecipeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface RecipeRepository extends JpaRepository<Recipe, RecipeId> {
    List<Recipe> findAllByEnable(boolean enable);
    Page<Recipe> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Recipe> findByDrinkAndMaterial(Drink drink, Material material);
    Optional<Recipe> findByRecipeIdDrinkIdAndRecipeIdMaterialIdAndEnable(
            Integer drinkId, Integer materialId, boolean enable);
    List<Recipe> findByDrinkIdAndEnable(Integer id, boolean enable);
    List<Recipe> findByDrinkId(Integer id);
    List<Recipe> findByMaterialId(Integer id);
    Optional<Recipe> findByRecipeIdIdAndEnable(Integer id, boolean enable);
    @Query("select max(r.recipeId.id) from Recipe r")
    Integer findMaxId();
}
