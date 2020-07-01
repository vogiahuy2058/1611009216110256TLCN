package coffeesystem.controller;

import coffeesystem.dto.*;
import coffeesystem.service.minMaxInventory.MinMaxInventoryService;
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

    public ResponseEntity<ResponseDto> fallBackGetMinMaxInventoryById(Integer idMaterial, Integer idBranchShop) {
        System.out.println("=======fallBackGetMinMaxInventoryById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllMinMaxInventory(){
        return ResponseEntity.ok(this.minMaxInventoryService.getAllMinMaxInventory());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllMinMaxInventory() {
        System.out.println("=======fallBackGetAllMinMaxInventory=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventoryPaging")
    public ResponseEntity<PagingResponseDto> getAllMinMaxInventoryPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "minMaxInventoryId.id") String sortColumn){
        return ResponseEntity.ok(this.minMaxInventoryService.getAllMinMaxInventoryPaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllMinMaxInventoryPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllMMinMaxInventoryPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-branch-shop-id-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventoryPaging")
    public ResponseEntity<PagingResponseDto> getMinMaxInventoryByBranchShopIdPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "minMaxInventoryId.id") String sortColumn,
            @RequestParam Integer idBranchShop){
        return ResponseEntity.ok(this.minMaxInventoryService.getAllByBranchShopIdPaging(page, size, sort,
                sortColumn, idBranchShop));
    }
    @GetMapping("/get-material-have-min-max")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getMaterialHaveMinMax(@RequestParam Integer idBranchShop){
        return ResponseEntity.ok(this.minMaxInventoryService.getMaterialHaveMinMaxByIdBranchShop(idBranchShop));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllMinMaxInventoryPaging(int page, int size, String sort,
                                                                                  String sortColumn, Integer idBranchShop) {
        System.out.println("=======fallBackGetAllMinMaxInventoryPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteMinMaxInventory(@RequestParam Integer id){
        return ResponseEntity.ok(minMaxInventoryService.deleteMinMaxInventory(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editMinMaxInventory(@RequestBody MinMaxInventoryRequestDto minMaxInventoryRequestDto){
        return ResponseEntity.ok(minMaxInventoryService.editMinMaxInventory(minMaxInventoryRequestDto));
    }
}
