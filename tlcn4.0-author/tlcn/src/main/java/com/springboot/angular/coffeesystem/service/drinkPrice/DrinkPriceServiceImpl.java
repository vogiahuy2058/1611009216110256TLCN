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

@Service
public class DrinkPriceServiceImpl implements DrinkPriceService{
    @Autowired
    DrinkPriceRepository drinkPriceRepository;
    @Autowired
    MapperObject mapperObject;
    @Autowired
    DrinkRepository drinkRepository;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ResponseDto createPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto){

        DrinkPrice drinkPrice = mapperObject.DrinkPriceDtoToEntity(drinkPriceRequestDto);
        Drink drink = drinkRepository.findByIdAndEnable(drinkPriceRequestDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        DrinkPriceId drinkPriceId = new DrinkPriceId();
        drinkPriceId.setId(drinkPriceRequestDto.getDrinkId());
        drinkPrice.setDrinkPriceId(drinkPriceId);
        drinkPrice.setDrink(drink);
        drinkPriceRepository.save(drinkPrice);
        return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);

    }
    public ResponseDto changePriceOrInitialPriceOfDrink(DrinkPriceRequestDto drinkPriceRequestDto){
        DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdAndEnable(drinkPriceRequestDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
        drinkPrice.setEnable(false);
        drinkPriceRepository.save(drinkPrice);

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
        DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdAndEnable(drinkId, true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
        DrinkPriceResponseDto drinkPriceResponseDto = mapperObject.DrinkPriceEntityToDto1(drinkPrice);
        drinkPriceResponseDto.setDrinkId(drinkPrice.getDrinkPriceId().getId());
        drinkPriceResponseDto.setDate(drinkPrice.getDrinkPriceId().getDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return new ResponseDto(HttpStatus.OK.value(), "Successful", drinkPriceResponseDto);
    }


}
