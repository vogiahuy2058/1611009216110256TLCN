package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.DrinkPriceRequestDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> createDrinkPrice(@RequestBody DrinkPriceRequestDto drinkPriceRequestDto){
        return ResponseEntity.ok(drinkPriceService.createPriceOfDrink(drinkPriceRequestDto));
    }
    @PutMapping("/change")
    public ResponseEntity<ResponseDto> changeDrinkPriceOrInitialPrice(@RequestBody DrinkPriceRequestDto drinkPriceRequestDto){
        return ResponseEntity.ok(drinkPriceService.changePriceOrInitialPriceOfDrink(drinkPriceRequestDto));
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getDrinkPriceByDrinkId(@RequestParam Integer drinkId){
        return ResponseEntity.ok(this.drinkPriceService.getPriceOfDrink(drinkId));
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllDrinkPrice(){
        return ResponseEntity.ok(this.drinkPriceService.getAllPriceOfDrink());
    }

}
