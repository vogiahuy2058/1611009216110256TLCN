package com.springboot.angular.coffeesystem.service.recipe;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.RecipeDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;

public interface RecipeService {
    ResponseDto createRecipe(RecipeDto recipeDto);
    ResponseDto getAllRecipe();
    ResponseDto deleteRecipe(Integer drinkId, Integer materialId);
    ResponseDto editRecipe(RecipeDto recipeDto);
    ResponseDto getRecipeByDrinkIdId(Integer id);
    PagingResponseDto getAllRecipePaging(int page, int size, String sort, String sortColumn);
}
