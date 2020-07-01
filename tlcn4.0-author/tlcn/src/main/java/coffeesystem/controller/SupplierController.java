package coffeesystem.controller;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplierDto;
import coffeesystem.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplier(@RequestBody SupplierDto supplierDto){
        return ResponseEntity.ok(supplierService.createSupplier(supplierDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllSupplierPaging")
    public ResponseEntity<PagingResponseDto> getAllSupplierPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.supplierService.getAllSupplierPaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllSupplierPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllSupplierPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllSupplyContract")
    public ResponseEntity<ResponseDto> getAllSupplier(){
        return  ResponseEntity.ok(this.supplierService.getAllSupplier());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllSupplyContract() {
        System.out.println("=======fallBackGetAllSupplyContract=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetSupplyContractById")
    public ResponseEntity<ResponseDto> getSupplierById(@RequestParam Integer id){
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetSupplyContractById(Integer id) {
        System.out.println("=======fallBackGetSupplyContractById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSupplier(@RequestParam Integer id){
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplier(@RequestBody SupplierDto supplierDto){
        return ResponseEntity.ok(supplierService.editSupplier(supplierDto));
    }
}
