package coffeesystem.controller;

import coffeesystem.dto.MaterialPriceRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.materialPrice.MaterialPriceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/material-price")
public class MaterialPriceController {
    @Autowired
    MaterialPriceService materialPriceService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMaterialPrice(@RequestBody MaterialPriceRequestDto materialPriceRequestDto){
        return ResponseEntity.ok(materialPriceService.createPriceOfMaterial(materialPriceRequestDto));
    }
    @PutMapping("/change")
    public ResponseEntity<ResponseDto> changeMaterialPrice(@RequestBody MaterialPriceRequestDto materialPriceRequestDto){
        return ResponseEntity.ok(materialPriceService.changePriceOfMaterial(materialPriceRequestDto));
    }
    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetMaterialPriceById")
    public ResponseEntity<ResponseDto> getMaterialByMaterialId(@RequestParam Integer materialId){
        return ResponseEntity.ok(this.materialPriceService.getPriceOfMaterial(materialId));
    }

    public ResponseEntity<ResponseDto> fallBackGetMaterialPriceById(Integer materialId) {
        System.out.println("=======fallBackGetMaterialPriceById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllPriceMaterial")
    public ResponseEntity<ResponseDto> getAllPriceMaterial(){
        return ResponseEntity.ok(this.materialPriceService.getAllPriceOfMaterial());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllMaterialPrice() {
        System.out.println("=======fallBackGetAllMaterialPrice=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllMaterialPricePaging")
    public ResponseEntity<PagingResponseDto> getAllMaterialPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "material.name") String sortColumn){
        return ResponseEntity.ok(this.materialPriceService.getAllMaterialPricePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllMaterialPricePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllMaterialPricePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
}
