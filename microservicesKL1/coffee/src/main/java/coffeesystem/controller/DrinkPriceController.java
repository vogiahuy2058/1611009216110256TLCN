package coffeesystem.controller;

import coffeesystem.dto.DrinkPriceRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.drinkPrice.DrinkPriceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @HystrixCommand(fallbackMethod = "fallBackGetDrinkPriceById")
    public ResponseEntity<ResponseDto> getDrinkPriceByDrinkId(@RequestParam Integer drinkId){
        return ResponseEntity.ok(this.drinkPriceService.getPriceOfDrink(drinkId));
    }
    public ResponseEntity<ResponseDto> fallBackGetDrinkPriceById(Integer drinkId) {
        System.out.println("=======fallBackGetDrinkPriceById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllDrinkPrice")
    public ResponseEntity<ResponseDto> getAllDrinkPrice(){
        return ResponseEntity.ok(this.drinkPriceService.getAllPriceOfDrink());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllDrinkPrice() {
        System.out.println("=======fallBackGetAllDrinkPrice=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllDrinkPricePaging")
    public ResponseEntity<PagingResponseDto> getAllDrinkPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "drink.name") String sortColumn){
        return ResponseEntity.ok(this.drinkPriceService.getAllDrinkPricePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllDrinkPricePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllDrinkPricePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

}
