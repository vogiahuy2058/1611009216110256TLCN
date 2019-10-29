package com.springboot.angular.coffeesystem.service.drinkPrice;

import com.springboot.angular.coffeesystem.dto.DrinkPriceDto;
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
    public ResponseDto createPriceOfDrink(DrinkPriceDto drinkPriceDto){
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DrinkPrice drinkPrice = mapperObject.DrinkPriceDtoToEntity(drinkPriceDto);
        Drink drink = drinkRepository.findByIdAndEnable(drinkPriceDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        DrinkPriceId drinkPriceId = new DrinkPriceId();
        drinkPriceId.setId(drinkPriceDto.getDrinkId());
        drinkPriceId.setDate(LocalDate.parse(drinkPriceDto.getDate(), dtf));
        drinkPrice.setDrinkPriceId(drinkPriceId);
        drinkPrice.setDrink(drink);
        drinkPriceRepository.save(drinkPrice);
        return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);

    }
    public ResponseDto changePriceOrInitialPriceOfDrink(DrinkPriceDto drinkPriceDto){
        DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdAndEnable(drinkPriceDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
        drinkPrice.setEnable(false);
        drinkPriceRepository.save(drinkPrice);

        if(drinkPriceDto.getInitialPrice() == 0){
            drinkPriceDto.setInitialPrice(drinkPrice.getInitialPrice());
        }
        if(drinkPriceDto.getPrice() == 0){
            drinkPriceDto.setPrice(drinkPrice.getPrice());
        }
        createPriceOfDrink(drinkPriceDto);
        return new ResponseDto(HttpStatus.OK.value(), "Change price successful", null);

    }
    @Transactional
    public ResponseDto getPriceOfDrink(Integer drinkId){
        DrinkPrice drinkPrice = drinkPriceRepository.findByDrinkPriceIdIdAndEnable(drinkId, true)
                .orElseThrow(()-> new NotFoundException("Id drink not found"));
        DrinkPriceDto drinkPriceDto = mapperObject.DrinkPriceEntityToDto(drinkPrice);
        drinkPriceDto.setDrinkId(drinkPrice.getDrinkPriceId().getId());
        drinkPriceDto.setDate(drinkPrice.getDrinkPriceId().getDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return new ResponseDto(HttpStatus.OK.value(), "Successful", drinkPriceDto);
    }


}
