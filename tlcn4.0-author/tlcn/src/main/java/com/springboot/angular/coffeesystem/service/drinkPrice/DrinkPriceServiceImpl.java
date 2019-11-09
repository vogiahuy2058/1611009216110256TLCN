package com.springboot.angular.coffeesystem.service.drinkPrice;

import com.springboot.angular.coffeesystem.dto.DrinkPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.DrinkPriceResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Drink;
import com.springboot.angular.coffeesystem.model.DrinkPrice;
import com.springboot.angular.coffeesystem.model.embedding.DrinkPriceId;
import com.springboot.angular.coffeesystem.repository.DrinkPriceRepository;
import com.springboot.angular.coffeesystem.repository.DrinkRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkPriceServiceImpl implements DrinkPriceService{
    @Autowired
    DrinkPriceRepository drinkPriceRepository;
    @Autowired
    MapperObject mapperObject;
    @Autowired
    DrinkRepository drinkRepository;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Transactional
    public ResponseDto createPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto){

        //neu drink price do da ton tai thì enable=false
        if(drinkPriceRepository.findByDrinkPriceIdIdDrinkAndEnable(drinkPriceRequestDto.getDrinkId(),
                true).isPresent()){
            DrinkPrice drinkPriceOld =
                    drinkPriceRepository.findByDrinkPriceIdIdDrinkAndEnable(drinkPriceRequestDto.getDrinkId(), true)
                    .orElseThrow(()-> new NotFoundException("Id Drink not found"));
            drinkPriceOld.setEnable(false);
            drinkPriceRepository.save(drinkPriceOld);
        }
        DrinkPrice drinkPrice = mapperObject.DrinkPriceDtoToEntity(drinkPriceRequestDto);
        Drink drink = drinkRepository.findByIdAndEnable(drinkPriceRequestDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        DrinkPriceId drinkPriceId = new DrinkPriceId();
        drinkPriceId.setIdDrink(drinkPriceRequestDto.getDrinkId());
        Integer idOld = drinkPriceRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        drinkPriceId.setId(idOld + 1);
        drinkPriceId.setDate(drinkPriceRequestDto.getDate());
        drinkPrice.setDrinkPriceId(drinkPriceId);
        drinkPrice.setDrink(drink);
        drinkPriceRepository.save(drinkPrice);
        return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);

    }
    public ResponseDto changePriceOrInitialPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto){
        DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdDrinkAndEnable(drinkPriceRequestDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
//        drinkPrice.setEnable(false);
//        drinkPriceRepository.save(drinkPrice);

        if(drinkPriceRequestDto.getInitialPrice() == 0){
            drinkPriceRequestDto.setInitialPrice(drinkPrice.getInitialPrice());
        }
        if(drinkPriceRequestDto.getPrice() == 0){
            drinkPriceRequestDto.setPrice(drinkPrice.getPrice());
        }
        createPriceOfDrink(drinkPriceRequestDto);
        return new ResponseDto(HttpStatus.OK.value(), "Change price successful", null);

    }
    @Transactional
    public ResponseDto getPriceOfDrink(Integer drinkId){
        DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdDrinkAndEnable(drinkId, true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
        Drink drink = drinkRepository.findByIdAndEnable(drinkId, true)
                .orElseThrow(()-> new NotFoundException("Dink id not found"));
        DrinkPriceResponseDto drinkPriceResponseDto = mapperObject.DrinkPriceEntityToDto1(drinkPrice);
        drinkPriceResponseDto.setId(drinkPrice.getDrinkPriceId().getId());
        drinkPriceResponseDto.setDrinkId(drinkPrice.getDrinkPriceId().getIdDrink());
        drinkPriceResponseDto.setDate(drinkPrice.getDrinkPriceId().getDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        drinkPriceResponseDto.setDrinkName(drink.getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", drinkPriceResponseDto);
    }
    @Transactional
    public ResponseDto getAllPriceOfDrink(){
        List<DrinkPrice> drinkPriceList = this.drinkPriceRepository.findAllByEnable(true);
        List<DrinkPriceResponseDto> drinkPriceResponseDtos = new ArrayList<>();
        drinkPriceList.forEach(element->{
            DrinkPriceResponseDto drinkPriceResponseDto = mapperObject.DrinkPriceEntityToDto1(element);
            Drink drink = drinkRepository.findByIdAndEnable(element.getDrinkPriceId().getIdDrink(), true)
                    .orElseThrow(()-> new NotFoundException("Dink id not found"));
            drinkPriceResponseDto.setId(element.getDrinkPriceId().getId());
            drinkPriceResponseDto.setDrinkId(element.getDrinkPriceId().getIdDrink());
            drinkPriceResponseDto.setDate(element.getDrinkPriceId().getDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            drinkPriceResponseDto.setDrinkName(drink.getName());
            drinkPriceResponseDtos.add(drinkPriceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All drink price", drinkPriceResponseDtos);
    }


}
