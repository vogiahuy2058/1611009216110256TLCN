package coffeesystem.controller;

import coffeesystem.dto.InventoryControlRequestDto;
import coffeesystem.dto.InventoryRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.model.InventoryControl;
import coffeesystem.service.inventory.InventoryService;
import coffeesystem.service.inventoryControl.InventoryControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/inventory-control")
public class InventoryControlController {
    @Autowired
    InventoryControlService inventoryControlService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInventoryControl(@RequestBody InventoryControlRequestDto inventoryControlRequestDto){
        return ResponseEntity.ok(inventoryControlService.createInventoryControl(inventoryControlRequestDto));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseDto> UpdateInventoryControl(@RequestBody InventoryControlRequestDto inventoryControlRequestDto){
        return ResponseEntity.ok(inventoryControlService.updateInventoryControl(inventoryControlRequestDto));
    }

//    @GetMapping("/get")
////    @HystrixCommand(fallbackMethod = "fallBackGetMinMaxInventoryById")
//    public ResponseEntity<ResponseDto> getInventoryByMaterialIdAndBranchShopIdAndFirstDate(@RequestParam Integer idMaterial,
//                                                                                           @RequestParam Integer idBranchShop,
//                                                                                           @RequestParam String firstDate){
//        return ResponseEntity.ok(this.inventoryService.getByIdMaterialAndIdBranchShopAndFirstDate(idMaterial, idBranchShop, firstDate));
//    }
//
//    public ResponseEntity<ResponseDto> fallBackGetMinMaxInventoryById(Integer idMaterial, Integer idBranchShop) {
//        System.out.println("=======fallBackGetMinMaxInventoryById=========");
//        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
//    }
//    @GetMapping("/get-by-material-branchshop")
////    @HystrixCommand(fallbackMethod = "fallBackGetMinMaxInventoryById")
//    public ResponseEntity<ResponseDto> getInventoryByMaterialIdAndBranchShopId(@RequestParam Integer idMaterial,
//                                                                               @RequestParam Integer idBranchShop){
//        return ResponseEntity.ok(this.inventoryService.getByIdMaterialAndIdBranchShop(idMaterial, idBranchShop));
//    }
    @GetMapping("/get-all-status-active")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllInventoryControlByStatusActive(){
        return ResponseEntity.ok(this.inventoryControlService.getAllInventoryControlStatusActive());
    }
    @GetMapping("/get-all-id-branch-shop-status-active")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllInventoryControlByIdBranchShopStatusActive(@RequestParam Integer idBranchShop){
        return ResponseEntity.ok(this.inventoryControlService.getAllInventoryControlByIdBranchShopStatusActive(idBranchShop));
    }
    @GetMapping("/get-all-id-material-id-bs-status-active")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllInventoryControlByIdMaterialIdBranchShopStatusActive(@RequestParam Integer idMaterial,
                                                                                                  @RequestParam Integer idBranchShop){
        return ResponseEntity.ok(this.inventoryControlService.getInventoryControlByIdMaterialIdBranchShopStatusActive(
                idMaterial,idBranchShop));
    }
    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllInventoryControl(){
        return ResponseEntity.ok(this.inventoryControlService.getAllInventoryControl());
    }
    @GetMapping("/get-material-existed-in-inventory-control")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> getAllInventoryControl(@RequestParam Integer idBranchShop){
        return ResponseEntity.ok(this.inventoryControlService.getMaterialExistInInventoryControlByIdBranchShop(idBranchShop));
    }
    @GetMapping("/edit-status")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> editStatusInventoryControl(@RequestParam Integer idMaterial,
                                                              @RequestParam Integer idBranchShop,
                                                              @RequestParam String firstDate,
                                                              @RequestParam String status){
        return ResponseEntity.ok(this.inventoryControlService.editStatusInventoryControl(
                idMaterial, idBranchShop, firstDate, status));
    }

    @GetMapping("/delete")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventory")
    public ResponseEntity<ResponseDto> deleteInventoryControl(@RequestParam Integer idMaterial,
                                                              @RequestParam Integer idBranchShop,
                                                              @RequestParam String firstDate){
        return ResponseEntity.ok(this.inventoryControlService.deleteInventoryControl(idMaterial, idBranchShop, firstDate));
    }

//    public ResponseEntity<ResponseDto> fallBackGetAllMinMaxInventory() {
//        System.out.println("=======fallBackGetAllMinMaxInventory=========");
//        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
//    }
//
//    @GetMapping("/get-all-paging")
////    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventoryPaging")
//    public ResponseEntity<PagingResponseDto> getAllInventoryPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "inventoryId.idBranchShop") String sortColumn){
//        return ResponseEntity.ok(this.inventoryService.getAllInventoryPaging(page, size, sort, sortColumn));
//    }
//    public ResponseEntity<PagingResponseDto> fallBackGetAllMinMaxInventoryPaging(int page, int size, String sort, String sortColumn) {
//        System.out.println("=======fallBackGetAllMMinMaxInventoryPaging=========");
//        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
//    }
//    @GetMapping("/get-by-branch-shop-id-paging")
////    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventoryPaging")
//    public ResponseEntity<PagingResponseDto> getMinMaxInventoryByBranchShopIdPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "inventoryId.idBranchShop") String sortColumn,
//            @RequestParam Integer idBranchShop){
//        return ResponseEntity.ok(this.inventoryService.getAllByBranchShopIdPaging(page, size, sort,
//                sortColumn, idBranchShop));
//    }
//    public ResponseEntity<PagingResponseDto> fallBackGetAllMinMaxInventoryPaging(int page, int size, String sort,
//                                                                                 String sortColumn, Integer idBranchShop) {
//        System.out.println("=======fallBackGetAllMinMaxInventoryPaging=========");
//        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
//    }
//    @GetMapping("/get-by-branch-shop-id-status-paging")
////    @HystrixCommand(fallbackMethod = "fallBackGetAllMinMaxInventoryPaging")
//    public ResponseEntity<PagingResponseDto> getMinMaxInventoryByBranchShopIdAndStatusPaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "inventoryId.idBranchShop") String sortColumn,
//            @RequestParam Integer idBranchShop,
//            @RequestParam String status){
//        return ResponseEntity.ok(this.inventoryService.getAllByBranchShopIdAndStatusPaging(page, size, sort,
//                sortColumn, idBranchShop, status));
//    }
//    @PutMapping("/delete")
//    public ResponseEntity<ResponseDto> deleteInventory(@RequestParam Integer id){
//        return ResponseEntity.ok(inventoryService.deleteInventory(id));
//    }
//    @PutMapping("/edit")
//    public ResponseEntity<ResponseDto> editInventory(@RequestBody InventoryRequestDto inventoryRequestDto){
//        return ResponseEntity.ok(inventoryService.editInventory(inventoryRequestDto));
//    }
}
