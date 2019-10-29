package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.DrinkPriceDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.DrinkPrice;
import com.springboot.angular.coffeesystem.service.drinkPrice.DrinkPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/drink-price")
public class DrinkPriceController {
    @Autowired
    DrinkPriceService drinkPriceService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDrinkPrice(@RequestBody DrinkPriceDto drinkPriceDto){
        return ResponseEntity.ok(drinkPriceService.createPriceOfDrink(drinkPriceDto));
    }
    @PutMapping("/change")
    public ResponseEntity<ResponseDto> changeDrinkPriceOrInitialPrice(@RequestBody DrinkPriceDto drinkPriceDto){
        return ResponseEntity.ok(drinkPriceService.changePriceOrInitialPriceOfDrink(drinkPriceDto));
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getDrinkByDrinkId(@RequestParam Integer drinkId){
        return ResponseEntity.ok(this.drinkPriceService.getPriceOfDrink(drinkId));
    }

}
