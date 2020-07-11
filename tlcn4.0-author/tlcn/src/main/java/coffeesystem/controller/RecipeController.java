package coffeesystem.controller;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.RecipeRequestDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.recipe.RecipeService;
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
    public ResponseEntity<ResponseDto> createRecipe(@RequestBody RecipeRequestDto recipeRequestDto){
        return ResponseEntity.ok(recipeService.createRecipe(recipeRequestDto));
    }
    @GetMapping("/get-all-paging")
    public ResponseEntity<PagingResponseDto> getAllRecipePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "recipeId.drinkId") String sortColumn){
        return ResponseEntity.ok(this.recipeService.getAllRecipePaging(page, size, sort, sortColumn));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllRecipe(){
        return  ResponseEntity.ok(this.recipeService.getAllRecipe());
    }

    @GetMapping("/get-by-drink")
    public ResponseEntity<ResponseDto> getRecipeByDrink(@RequestParam Integer idDrink){
        return ResponseEntity.ok(recipeService.getRecipeByDrinkIdId(idDrink));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteRecipe(@RequestParam Integer idDrink){
        return ResponseEntity.ok(recipeService.deleteRecipe(idDrink));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editDrinkType(@RequestBody RecipeRequestDto recipeRequestDto){
        return ResponseEntity.ok(recipeService.editRecipe(recipeRequestDto));
    }
}
