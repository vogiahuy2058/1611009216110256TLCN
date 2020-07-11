package coffeesystem.service.recipe;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.RecipeRequestDto;
import coffeesystem.dto.RecipeResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Drink;
import coffeesystem.model.Material;
import coffeesystem.model.Recipe;
import coffeesystem.model.Unit;
import coffeesystem.model.embedding.RecipeId;
import coffeesystem.repository.*;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
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
    @Autowired
    DrinkPriceRepository drinkPriceRepository;
    @Autowired
    UnitRepository unitRepository;
    public ResponseDto createRecipe(RecipeRequestDto recipeRequestDto){
        Recipe recipe = this.mapperObject.RecipeDtoToEntity1(recipeRequestDto);
        Drink drink = drinkRepository.findByIdAndEnable(recipeRequestDto.getDrinkId(),true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Material material = materialRepository.findByIdAndEnable(recipeRequestDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));

        RecipeId recipeId = new RecipeId();
        Integer idOld = recipeRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        recipeId.setId(idOld + 1);
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
        List<RecipeResponseDto> recipeResponseDtos = new ArrayList<>();
        recipes.forEach(element->{
            RecipeResponseDto recipeResponseDto = mapperObject.RecipeEntityToDto2(element);
            recipeResponseDto.setId(element.getRecipeId().getId());
            recipeResponseDto.setDrinkId(element.getDrink().getId());
            recipeResponseDto.setMaterialId(element.getMaterial().getId());
            recipeResponseDto.setDrinkName(element.getDrink().getName());
            recipeResponseDto.setMaterialName(element.getMaterial().getName());
            recipeResponseDto.setUnitName(element.getMaterial().getUnit().getName());
            recipeResponseDtos.add(recipeResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All recipes", recipeResponseDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllRecipePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<RecipeResponseDto> recipeResponseDtos = new ArrayList<>();
        Page<Recipe> recipePage = recipeRepository.findAllByEnable(true, pageable);
        recipePage.forEach(element->{
            RecipeResponseDto recipeResponseDto = mapperObject.RecipeEntityToDto2(element);
            recipeResponseDto.setId(element.getRecipeId().getId());
            recipeResponseDto.setDrinkId(element.getDrink().getId());
            recipeResponseDto.setMaterialId(element.getMaterial().getId());
            recipeResponseDto.setDrinkName(element.getDrink().getName());
            recipeResponseDto.setMaterialName(element.getMaterial().getName());
            recipeResponseDto.setUnitName(element.getMaterial().getUnit().getName());
            recipeResponseDtos.add(recipeResponseDto);});
        Page<RecipeResponseDto> recipeResponseDtoPage = new PageImpl<>(recipeResponseDtos, pageable,
                recipePage.getTotalElements() );
        return new PagingResponseDto<>(
                recipeResponseDtoPage.getContent(), recipeResponseDtoPage.getTotalElements(),
                recipeResponseDtoPage.getTotalPages(),
                recipeResponseDtoPage.getPageable());
    }
    public ResponseDto deleteRecipe(Integer idDrink){

        List<Recipe> recipeList = recipeRepository.findByDrinkIdAndEnable(idDrink, true);
        recipeList.forEach(element->{
            element.setEnable(false);
            recipeRepository.save(element);
        });

        return new ResponseDto(HttpStatus.OK.value(),
                "Delete recipe successful", null);
    }
    public ResponseDto editRecipe(RecipeRequestDto recipeRequestDto){
        Recipe recipe = recipeRepository.findByRecipeIdIdAndEnable(recipeRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Recipe not found"));
        recipe.setMinAmount(recipeRequestDto.getMinAmount());
        recipe.setMaxAmount(recipeRequestDto.getMaxAmount());
        recipeRepository.save(recipe);
        return new ResponseDto(HttpStatus.OK.value(), "Edit recipe successful", null);
    }
    @Transactional
    public ResponseDto getRecipeByDrinkIdId(Integer idDrink){
        List<Recipe> recipes = recipeRepository.findByDrinkIdAndEnable(idDrink,true);
        List<RecipeResponseDto> recipeResponseDtos = new ArrayList<>();
        recipes.forEach(element->{
            RecipeResponseDto recipeResponseDto = mapperObject.RecipeEntityToDto2(element);
            recipeResponseDto.setId(element.getRecipeId().getId());
            recipeResponseDto.setDrinkId(element.getDrink().getId());
            recipeResponseDto.setMaterialId(element.getMaterial().getId());
            recipeResponseDto.setDrinkName(element.getDrink().getName());
            recipeResponseDto.setMaterialName(element.getMaterial().getName());
            recipeResponseDto.setUnitName(element.getMaterial().getUnit().getName());
            recipeResponseDtos.add(recipeResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "Recipe of drink have id : " + idDrink,
                recipeResponseDtos);
    }
}
