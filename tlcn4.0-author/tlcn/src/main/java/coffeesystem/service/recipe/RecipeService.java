package coffeesystem.service.recipe;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.RecipeRequestDto;
import coffeesystem.dto.ResponseDto;

public interface RecipeService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto createRecipe(RecipeRequestDto recipeRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllRecipe();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto deleteRecipe(Integer idDrink);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editRecipe(RecipeRequestDto recipeRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getRecipeByDrinkIdId(Integer idDrink);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CHEF', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllRecipePaging(int page, int size, String sort, String sortColumn);
}
