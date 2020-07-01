package coffeesystem.controller;

import coffeesystem.dto.CustomerTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.customerType.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/customer-type")
public class CustomerTypeController {
    @Autowired
    CustomerTypeService customerTypeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomerType(@RequestBody CustomerTypeDto customerTypeDto){
        return ResponseEntity.ok(customerTypeService.createCustomerType(customerTypeDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllCustomerTypePaging")
    public ResponseEntity<PagingResponseDto> getAllCustomerTypePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.customerTypeService.getAllCustomerTypePaging(page, size, sort, sortColumn));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllCustomerTypePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllCustomerTypePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllCustomerType")
    public ResponseEntity<ResponseDto> getAllCustomerType(){
        return  ResponseEntity.ok(this.customerTypeService.getAllCustomerType());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllCustomerType() {
        System.out.println("=======fallBackGetAllCustomerType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetCustomerTypeById")
    public ResponseEntity<ResponseDto> getCustomerTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(customerTypeService.getCustomerTypeById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetCustomerTypeById(Integer id) {
        System.out.println("=======fallBackGetCustomerTypeById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomerType(@RequestParam Integer id){
        return ResponseEntity.ok(customerTypeService.deleteCustomerType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editCustomerType(@RequestBody CustomerTypeDto customerTypeDto){
        return ResponseEntity.ok(customerTypeService.editCustomerType(customerTypeDto));
    }
}
