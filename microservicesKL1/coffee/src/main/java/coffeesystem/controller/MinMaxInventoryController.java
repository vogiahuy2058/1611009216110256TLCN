package coffeesystem.controller;

import coffeesystem.dto.*;
import coffeesystem.service.minMaxInventory.MinMaxInventoryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/min-max-inventory")
public class MinMaxInventoryController {
    @Autowired
    MinMaxInventoryService minMaxInventoryService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMinMaxInventory(@RequestBody MinMaxInventoryRequestDto minMaxInventoryRequestDto){
        return ResponseEntity.ok(minMaxInventoryService.createMinMaxInventory(minMaxInventoryRequestDto));
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetMinMaxInventoryById")
    public ResponseEntity<ResponseDto> getMinMaxInventoryByMaterialIđAnBranchShopId(@RequestParam Integer idMaterial,
                                                                      @RequestParam Integer idBranchShop){
        return ResponseEntity.ok(this.minMaxInventoryService.getMinMaxByIdMaterialAndIdBranchShop(idMaterial, idBranchShop));
    }

    public ResponseEntity<ResponseDto> fallBackGetMaterialPriceById(Integer materialId) {
        System.out.println("=======fallBackGetMaterialPriceById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllMinMaxInventory(){
        return ResponseEntity.ok(this.minMaxInventoryService.getAllMinMaxInventory());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllPriceMaterial() {
        System.out.println("=======fallBackGetAllPriceMaterial=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMMinMaxInventoryPaging")
    public ResponseEntity<PagingResponseDto> getAllMinMaxInventoryPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "minMaxInventoryId.id") String sortColumn){
        return ResponseEntity.ok(this.minMaxInventoryService.getAllMinMaxInventoryPaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllMaterialPricePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllMaterialPricePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteMinMaxInventory(@RequestParam Integer idMaterial,
                                                             @RequestParam Integer idBranchShop){
        return ResponseEntity.ok(minMaxInventoryService.deleteMinMaxInventory(idMaterial,idBranchShop));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editMinMaxInventory(@RequestBody MinMaxInventoryRequestDto minMaxInventoryRequestDto){
        return ResponseEntity.ok(minMaxInventoryService.editMinMaxInventory(minMaxInventoryRequestDto));
    }
}
