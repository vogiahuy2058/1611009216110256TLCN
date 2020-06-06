package coffeesystem.service.drink;

import coffeesystem.dto.DrinkDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Drink;
import coffeesystem.model.DrinkPrice;
import coffeesystem.model.DrinkType;
import coffeesystem.model.Recipe;
import coffeesystem.repository.DrinkPriceRepository;
import coffeesystem.repository.DrinkRepository;
import coffeesystem.repository.DrinkTypeRepository;
import coffeesystem.repository.RecipeRepository;
import coffeesystem.service.recipe.RecipeService;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    @Autowired
    DrinkPriceRepository drinkPriceRepository;
    public ResponseDto createDrink(DrinkDto drinkDto){

        Drink drink = this.mapperObject.DrinkDTOToDrinkEntity(drinkDto);
        DrinkType drinkType = drinkTypeRepository.findByNameAndEnable(drinkDto.getDrinkType(), true)
                .orElseThrow(()-> new NotFoundException("Drink type not found"));
        drink.setDrinkType(drinkType);
        this.drinkRepository.save(drink);

        return new ResponseDto(HttpStatus.OK.value(), "Create drink successful", null);
    }
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
    @Transactional
    public ResponseDto getAllDrinkByDrinkType(String drinkTypeName){
        DrinkType drinkType = drinkTypeRepository.findByNameAndEnable(drinkTypeName, true)
                .orElseThrow(()-> new NotFoundException("Drink type not found"));
        List<Drink> drinkList = this.drinkRepository.findByDrinkTypeNameAndEnable(drinkTypeName,true);
        List<DrinkDto> drinkDtos = new ArrayList<>();
        drinkList.forEach(element->{
            DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(element);
            drinkDto.setDrinkType(element.getDrinkType().getName());
            drinkDtos.add(drinkDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All drink", drinkDtos);
    }
    @Transactional
    public ResponseDto getDrinkHavePriceByDrinkType(String nameDrinkType){
        DrinkType drinkType = drinkTypeRepository.findByNameAndEnable(nameDrinkType, true)
                .orElseThrow(()-> new NotFoundException("Drink type not found"));
        List<Drink> drinkList = this.drinkRepository.findDrinkHavePriceByDrinkType(nameDrinkType);
        List<DrinkDto> drinkDtos = new ArrayList<>();
        drinkList.forEach(element->{
            DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(element);
            drinkDto.setDrinkType(element.getDrinkType().getName());
            drinkDtos.add(drinkDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All drink", drinkDtos);
    }
    @Transactional
    public ResponseDto getAllDrinkHavePrice(){
        List<Drink> drinkList = this.drinkRepository.findAllDrinkHavePrice();
        List<DrinkDto> drinkDtos = new ArrayList<>();
        drinkList.forEach(element->{
            DrinkDto drinkDto = mapperObject.DrinkEntityToDrinkDTO(element);
            drinkDto.setDrinkType(element.getDrinkType().getName());
            drinkDtos.add(drinkDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All drink", drinkDtos);
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
        drink.setName(drinkDto.getName());
        drink.setDescription(drinkDto.getDescription());
        drinkRepository.save(drink);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink successful", null);
    }
    public ResponseDto deleteDrink(Integer id){
        Drink drink = drinkRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
        //delete recipe when drink was deleted
        List<Recipe> recipes = recipeRepository.findByDrinkId(id);
        recipes.forEach(element->{
            recipeService.deleteRecipe(element.getDrink().getId(),
                    element.getMaterial().getId());
        });
        ///delete drink price when drink was deleted
        if(drinkPriceRepository.findByDrinkPriceIdIdDrinkAndEnable(id, true).isPresent()){
            DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdDrinkAndEnable(id, true)
                    .orElseThrow(()-> new NotFoundException("Drink price not fount"));
            drinkPrice.setEnable(false);
            drinkPriceRepository.save(drinkPrice);
        }

        drink.setEnable(false);
        drinkRepository.save(drink);
        return new ResponseDto(HttpStatus.OK.value(), "Delete drink successful", null);
    }
}
