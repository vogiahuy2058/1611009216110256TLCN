package com.springboot.angular.coffeesystem.service.drink;

import com.springboot.angular.coffeesystem.dto.DrinkDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Drink;
import com.springboot.angular.coffeesystem.model.DrinkType;
import com.springboot.angular.coffeesystem.model.Recipe;
import com.springboot.angular.coffeesystem.repository.DrinkRepository;
import com.springboot.angular.coffeesystem.repository.DrinkTypeRepository;
import com.springboot.angular.coffeesystem.repository.RecipeRepository;
import com.springboot.angular.coffeesystem.service.recipe.RecipeService;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private MapperObject mapperObject;
    @Autowired
    DrinkTypeRepository drinkTypeRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeService recipeService;

    @Transactional
    public ResponseDto getAllDrink(){

        List<Drink> drinkList = this.drinkRepository.findAllByEnable(true);
        List<DrinkDto> drinkDtos = new ArrayList<>();
        drinkList.forEach(element->{
            DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(element);
            drinkDto.setDrinkType(element.getDrinkType().getName());
            drinkDtos.add(drinkDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All drink", drinkDtos);
    }

    @Override
    @Transactional
    public PagingResponseDto getAllDrinkPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<DrinkDto> drinkDtos = new ArrayList<>();
        Page<Drink> drinkPage = drinkRepository.findAllByEnable(true, pageable);

        drinkPage.forEach(element->{
            DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(element);
            drinkDto.setDrinkType(element.getDrinkType().getName());
            drinkDtos.add(drinkDto);});
        Page<DrinkDto> drinkDtoPage = new PageImpl<>(drinkDtos, pageable,
                drinkPage.getTotalElements());
        return new PagingResponseDto<>(
                drinkDtoPage.getContent(), drinkDtoPage.getTotalElements(), drinkDtoPage.getTotalPages(),
                drinkDtoPage.getPageable());
    }


    public ResponseDto createDrink(DrinkDto drinkDto){

        Drink drink = this.mapperObject.DrinkDTOToDrinkEntity(drinkDto);
        DrinkType drinkType = drinkTypeRepository.findByNameAndEnable(drinkDto.getDrinkType(), true)
                .orElseThrow(()-> new NotFoundException("Drink type not found"));
        drink.setDrinkType(drinkType);
        this.drinkRepository.save(drink);

        return new ResponseDto(HttpStatus.OK.value(), "Create drink successful", null);
    }
    @Transactional
    public ResponseDto getDrinkById(Integer id){
        Drink drink = drinkRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(drink);
        drinkDto.setDrinkType(drink.getDrinkType().getName());

        return new ResponseDto(HttpStatus.OK.value(), "Successful", drinkDto);
    }
    public ResponseDto editDrink(DrinkDto drinkDto){

        Drink drink = drinkRepository.findByIdAndEnable(drinkDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        DrinkType drinkType = drinkTypeRepository.findByNameAndEnable(drinkDto.getDrinkType(), true)
                .orElseThrow(()-> new NotFoundException("Drink type not found"));
        drink.setDrinkType(drinkType);
        drinkRepository.save(drink);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink successful", null);
    }
    public ResponseDto deleteDrink(Integer id){
        Drink drink = drinkRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        //delete recipe when drink was deleted
        List<Recipe> recipes = recipeRepository.findByDrinkId(id);
        recipes.forEach(element->{
            recipeService.deleteRecipe(element.getDrink().getId(),
                    element.getMaterial().getId());
        });
        drink.setEnable(false);
        drinkRepository.save(drink);
        return new ResponseDto(HttpStatus.OK.value(), "Delete drink successful", null);
    }
//    public ResponseDto changePrice(Integer id, float newPrice){
//        Drink drink = drinkRepository.findByDrinkIdIdAndEnable(id, true)
//                .orElseThrow(()-> new NotFoundException("Drink not found"));
//        drink.setEnable(false);
//        drinkRepository.save(drink);
//
//        DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(drink);
//        drinkDto.setId(drink.getDrinkId().getId());
//        drinkDto.setDate(LocalDate.now());
//        drinkDto.setDrinkType(drink.getDrinkType().getName());
//        drinkDto.setPrice(newPrice);
//        createDrink(drinkDto);
//        return new ResponseDto(HttpStatus.OK.value(), "Price is changed successfully", null);
//    }





}
