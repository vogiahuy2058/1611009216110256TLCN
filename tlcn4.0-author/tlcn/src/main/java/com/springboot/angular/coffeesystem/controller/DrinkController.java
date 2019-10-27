package com.springboot.angular.coffeesystem.controller;

import com.springboot.angular.coffeesystem.dto.DrinkDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.service.drink.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drink")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllDrinkPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
//        return ResponseEntity.ok(this.drinkService.getAllDrinkPaging(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllDrink(){
        return  ResponseEntity.ok(this.drinkService.getAllDrink());
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDrink(@RequestBody DrinkDto drinkDTO) {

        return ResponseEntity.ok(this.drinkService.createDrink(drinkDTO));
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> getDrinkByDrinkIdid(@RequestParam Integer id){
        return ResponseEntity.ok(this.drinkService.getDrinkById(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editDrink(@RequestBody DrinkDto drinkDto){
        return ResponseEntity.ok(drinkService.editDrink(drinkDto));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDrink(@RequestBody Integer id){
        return ResponseEntity.ok(drinkService.deleteDrink(id));
    }
//    @PostMapping("change-price")
//    public ResponseEntity<ResponseDto> changePrice(@RequestParam Integer id,
//                                                   @RequestParam float newPrice){
//        return ResponseEntity.ok(drinkService.changePrice(id,newPrice));
//    }

}
