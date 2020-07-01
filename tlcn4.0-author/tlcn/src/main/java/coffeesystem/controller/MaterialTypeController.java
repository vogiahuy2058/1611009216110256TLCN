package coffeesystem.controller;

import coffeesystem.dto.MaterialTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.materialType.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/material-type")
public class MaterialTypeController {
    @Autowired
    MaterialTypeService materialTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createMaterialType(@RequestBody MaterialTypeDto materialTypeDto){
        return ResponseEntity.ok(materialTypeService.createMaterialType(materialTypeDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMaterialTypePaging")
    public ResponseEntity<PagingResponseDto> getAllMaterialTypePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.materialTypeService.getAllMaterialTypePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllMaterialTypePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllMaterialTypePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllMaterialType")
    public ResponseEntity<ResponseDto> getAllMaterialType(){
        return  ResponseEntity.ok(this.materialTypeService.getAllMaterialType());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllMaterialType() {
        System.out.println("=======fallBackGetAllMaterialType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetMaterialTypeById")
    public ResponseEntity<ResponseDto> getMaterialTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(materialTypeService.getMaterialTypeById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetMaterialTypeById(Integer id) {
        System.out.println("=======fallBackGetMaterialTypeById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteMaterialType(@RequestParam Integer id){
        return ResponseEntity.ok(materialTypeService.deleteMaterialType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editMaterialType(@RequestBody MaterialTypeDto materialTypeDto){
        return ResponseEntity.ok(materialTypeService.editMaterialType(materialTypeDto));
    }
}
