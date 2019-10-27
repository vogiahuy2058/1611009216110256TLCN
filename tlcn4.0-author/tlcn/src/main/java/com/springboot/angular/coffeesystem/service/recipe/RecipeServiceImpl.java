package com.springboot.angular.coffeesystem.service.recipe;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.RecipeDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Drink;
import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.Recipe;
import com.springboot.angular.coffeesystem.model.embedding.RecipeId;
import com.springboot.angular.coffeesystem.repository.DrinkRepository;
import com.springboot.angular.coffeesystem.repository.MaterialRepository;
import com.springboot.angular.coffeesystem.repository.RecipeRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    MaterialRepository materialRepository;
    public ResponseDto createRecipe(RecipeDto recipeDto){
        Recipe recipe = this.mapperObject.RecipeDtoToEntity(recipeDto);
        Drink drink = drinkRepository.findByNameAndEnable(recipeDto.getDrink(),true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Material material = materialRepository.findByNameAndEnable(recipeDto.getMaterial(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        RecipeId recipeId = new RecipeId();
        recipeId.setDrinkId(drink.getId());
        recipeId.setMaterialId(material.getId());
        recipe.setRecipeId(recipeId);
        recipe.setMaterial(material);
        recipe.setDrink(drink);
        recipeRepository.save(recipe);
        return new ResponseDto(HttpStatus.OK.value(), "Create recipe successful", null);
    }
    @Transactional
    public ResponseDto getAllRecipe(){
        List<Recipe> recipes = recipeRepository.findAllByEnable(true);
        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipes.forEach(element->{
            RecipeDto recipeDto = mapperObject.RecipeEntityToDto(element);
            recipeDto.setDrink(element.getDrink().getName());
            recipeDto.setMaterial(element.getMaterial().getName());
            recipeDtos.add(recipeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All recipes", recipeDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllRecipePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<RecipeDto> recipeDtos = new ArrayList<>();
        Page<Recipe> recipePage = recipeRepository.findAllByEnable(true, pageable);
        recipePage.forEach(element->{
            RecipeDto recipeDto = mapperObject.RecipeEntityToDto(element);
            recipeDto.setDrink(element.getDrink().getName());
            recipeDto.setMaterial(element.getMaterial().getName());
            recipeDtos.add(recipeDto);});
        Page<RecipeDto> recipeDtoPage = new PageImpl<>(recipeDtos, pageable,
                recipePage.getTotalElements() );
        return new PagingResponseDto<>(
                recipeDtoPage.getContent(), recipeDtoPage.getTotalElements(), recipeDtoPage.getTotalPages(),
                recipeDtoPage.getPageable());
    }
    public ResponseDto deleteRecipe(Integer drinkId, Integer materialId){
        Drink drink = drinkRepository.findByIdAndEnable(drinkId, true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Material material = materialRepository.findByIdAndEnable(materialId, true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Recipe recipe = recipeRepository.findByDrinkAndMaterial(drink, material)
                .orElseThrow(()-> new NotFoundException("Recipe not found"));
        recipe.setEnable(false);
        recipeRepository.save(recipe);
        return new ResponseDto(HttpStatus.OK.value(),
                "Delete recipe successful", null);
    }
    public ResponseDto editRecipe(RecipeDto recipeDto){
        Drink drink = drinkRepository.findByNameAndEnable(recipeDto.getDrink(), true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Material material = materialRepository.findByNameAndEnable(recipeDto.getMaterial(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Recipe recipe = recipeRepository.findByDrinkAndMaterial(drink, material)
                .orElseThrow(()-> new NotFoundException("Recipe not found"));
        recipe.setAmount(recipeDto.getAmount());
        recipe.setUnit(recipeDto.getUnit());
        recipe.setDrink(drink);
        recipe.setMaterial(material);
        recipeRepository.save(recipe);
        return new ResponseDto(HttpStatus.OK.value(), "Edit recipe successful", null);
    }
    @Transactional
    public ResponseDto getRecipeByDrinkIdId(Integer id){
        List<Recipe> recipe = recipeRepository.findByDrinkIdAndEnable(id, true);
        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipe.forEach(element->{
            RecipeDto recipeDto = mapperObject.RecipeEntityToDto(element);
            recipeDto.setDrink(element.getDrink().getName());
            recipeDto.setMaterial(element.getMaterial().getName());
            recipeDtos.add(recipeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All recipes", recipeDtos);
    }
}
