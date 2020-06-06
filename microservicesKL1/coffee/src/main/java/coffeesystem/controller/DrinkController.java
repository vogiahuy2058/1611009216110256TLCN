package coffeesystem.controller;

import coffeesystem.dto.DrinkDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.drink.DrinkService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/drink")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllDrinkPaging")
    public ResponseEntity<PagingResponseDto> getAllDrinkPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.drinkService.getAllDrinkPaging(page, size, sort, sortColumn));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllDrinkPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllDrinkPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllDrink")
    public ResponseEntity<ResponseDto> getAllDrink(){
        return  ResponseEntity.ok(this.drinkService.getAllDrink());
    }

    public ResponseEntity<ResponseDto> fallBackGetAllDrink() {
        System.out.println("=======fallBackGetAllDrink=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-by-drinkType-haveprice")
    @HystrixCommand(fallbackMethod = "fallBackGetDrinkHavePriceByDrinkType")
    public ResponseEntity<ResponseDto> getDrinkHavePriceByDrinkType(@RequestParam String drinkTypeName){
        return  ResponseEntity.ok(this.drinkService.getDrinkHavePriceByDrinkType(drinkTypeName));
    }

    public ResponseEntity<ResponseDto> fallBackGetDrinkHavePriceByDrinkType(String drinkTypeName) {
        System.out.println("=======fallBackGetDrinkHavePriceByDrinkType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-haveprice")
    @HystrixCommand(fallbackMethod = "fallBackGetDrinkHavePrice")
    public ResponseEntity<ResponseDto> getAllDrinkHavePrice(){
        return  ResponseEntity.ok(this.drinkService.getAllDrinkHavePrice());
    }
    public ResponseEntity<ResponseDto> fallBackGetDrinkHavePrice() {
        System.out.println("=======fallBackGetDrinkHavePrice=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-by-drinkType")
    @HystrixCommand(fallbackMethod = "fallBackGetDrinkByDrinkType")
    public ResponseEntity<ResponseDto> getDrinkByDrinkType(@RequestParam String drinkTypeName){
        return  ResponseEntity.ok(this.drinkService.getAllDrinkByDrinkType(drinkTypeName));
    }
    public ResponseEntity<ResponseDto> fallBackGetDrinkByDrinkType(String drinkTypeName) {
        System.out.println("=======fallBackGetDrinkByDrinkType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDrink(@RequestBody DrinkDto drinkDTO) {

        return ResponseEntity.ok(this.drinkService.createDrink(drinkDTO));
    }
    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetDrinkById")
    public ResponseEntity<ResponseDto> getDrinkByDrinkId(@RequestParam Integer id){
        return ResponseEntity.ok(this.drinkService.getDrinkById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetDrinkById(Integer id) {
        System.out.println("=======fallBackGetDrinkById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editDrink(@RequestBody DrinkDto drinkDto){
        return ResponseEntity.ok(drinkService.editDrink(drinkDto));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteDrink(@RequestParam Integer id){
        return ResponseEntity.ok(drinkService.deleteDrink(id));
    }
//    @PostMapping("change-price")
//    public ResponseEntity<ResponseDto> changePrice(@RequestParam Integer id,
//                                                   @RequestParam float newPrice){
//        return ResponseEntity.ok(drinkService.changePrice(id,newPrice));
//    }

}
