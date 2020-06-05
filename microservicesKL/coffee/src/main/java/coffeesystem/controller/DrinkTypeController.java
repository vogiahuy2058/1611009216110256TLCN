package coffeesystem.controller;

import coffeesystem.dto.DrinkTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.drinkType.DrinkTypeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/drink-type")
public class DrinkTypeController {
    @Autowired
    DrinkTypeService drinkTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDrinkType(@RequestBody DrinkTypeDto drinkTypeDto){
        return ResponseEntity.ok(drinkTypeService.createDrinkType(drinkTypeDto));
    }
    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllDrinkTypePaging")
    public ResponseEntity<PagingResponseDto> getAllDrinkPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.drinkTypeService.getAllDrinkTypePaging(page, size, sort, sortColumn));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllDrinkTypePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllDrinkTypePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllDrinkType")
    public ResponseEntity<ResponseDto> getAllDrinkType(){
        return  ResponseEntity.ok(this.drinkTypeService.getAllDrinkType());
    }

    public ResponseEntity<ResponseDto> fallBackGetAllDrinkType() {
        System.out.println("=======fallBackGetAllDrinkType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetDrinkTypeById")
    public ResponseEntity<ResponseDto> getDrinkTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(drinkTypeService.getDrinkTypeById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetDrinkTypeById(Integer id) {
        System.out.println("=======fallBackGetDrinkTypeById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDrinkType(@RequestParam Integer id){
        return ResponseEntity.ok(drinkTypeService.deleteDrinkType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editDrinkType(@RequestBody DrinkTypeDto drinkTypeDto){
        return ResponseEntity.ok(drinkTypeService.editDrinkType(drinkTypeDto));
    }
}
