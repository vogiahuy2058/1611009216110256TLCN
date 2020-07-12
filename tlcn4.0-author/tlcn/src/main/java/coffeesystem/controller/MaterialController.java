package coffeesystem.controller;

import coffeesystem.dto.MaterialDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/material")
public class MaterialController {
    @Autowired
    MaterialService materialService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMaterial(@RequestBody MaterialDto materialDto){
        return ResponseEntity.ok(materialService.createMaterial(materialDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMaterialPaging")
    public ResponseEntity<PagingResponseDto> getAllMaterialPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.materialService.getAllMaterialPaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllMaterialPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllMaterialPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMaterial")
    public ResponseEntity<ResponseDto> getAllMaterial(){
        return  ResponseEntity.ok(this.materialService.getAllMaterial());
    }
    @GetMapping("/get-all-id-name")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMaterial")
    public ResponseEntity<ResponseDto> getAllMaterialShowIdAndName(){
        return  ResponseEntity.ok(this.materialService.getAllMaterialShowIdAndName());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllMaterial() {
        System.out.println("=======fallBackGetAllMaterial=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetMaterialById")
    public ResponseEntity<ResponseDto> getMaterialById(@RequestParam Integer id){
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetMaterialById(Integer id) {
        System.out.println("=======fallBackGetMaterialById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInvoiceId(){

        return ResponseEntity.ok(materialService.getMaxIdMaterial());
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteMaterial(@RequestParam Integer id){
        return ResponseEntity.ok(materialService.deleteMaterial(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editMaterial(@RequestBody MaterialDto materialDto){
        return ResponseEntity.ok(materialService.editMaterial(materialDto));
    }
//    @PostMapping("change-price")
//    public ResponseEntity<ResponseDto> changePrice(@RequestParam Integer id,
//                                                   @RequestParam float newPrice){
//        return ResponseEntity.ok(materialService.changePrice(id,newPrice));
//    }
}
