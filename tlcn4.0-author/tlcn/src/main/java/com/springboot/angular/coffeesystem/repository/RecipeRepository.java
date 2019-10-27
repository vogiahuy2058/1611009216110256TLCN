package com.springboot.angular.coffeesystem.repository;

import com.springboot.angular.coffeesystem.model.Drink;
import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.Recipe;
import com.springboot.angular.coffeesystem.model.embedding.RecipeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public interface RecipeRepository extends JpaRepository<Recipe, RecipeId> {
    List<Recipe> findAllByEnable(boolean enable);
    Page<Recipe> findAllByEnable(boolean enable, Pageable pageable);
    Optional<Recipe> findByDrinkAndMaterial(Drink drink, Material material);
    List<Recipe> findByDrinkIdAndEnable(Integer id, boolean enable);
    List<Recipe> findByDrinkId(Integer id);
    List<Recipe> findByMaterialId(Integer id);
}
