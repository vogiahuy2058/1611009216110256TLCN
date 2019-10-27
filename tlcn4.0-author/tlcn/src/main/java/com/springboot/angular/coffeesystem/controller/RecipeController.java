package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.RecipeDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createRecipe(@RequestBody RecipeDto recipeDto){
        return ResponseEntity.ok(recipeService.createRecipe(recipeDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllRecipePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "drink") String sortColumn){
//        return ResponseEntity.ok(this.recipeService.getAllRecipePaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllRecipe(){
        return  ResponseEntity.ok(this.recipeService.getAllRecipe());
    }

    @GetMapping("/get-by-drink")
    public ResponseEntity<ResponseDto> getRecipeByDrink(@RequestParam Integer id){
        return ResponseEntity.ok(recipeService.getRecipeByDrinkIdId(id));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteRecipe(@RequestParam Integer drinkId, Integer materialId){
        return ResponseEntity.ok(recipeService.deleteRecipe(drinkId, materialId));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editDrinkType(@RequestBody RecipeDto recipeDto){
        return ResponseEntity.ok(recipeService.editRecipe(recipeDto));
    }
}
