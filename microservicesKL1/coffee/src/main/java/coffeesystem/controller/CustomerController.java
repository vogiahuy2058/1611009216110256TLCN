package coffeesystem.controller;

import coffeesystem.dto.CustomerRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.customer.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok(customerService.createCustomer(customerRequestDto));
    }
    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllCustomerPaging")
    public ResponseEntity<PagingResponseDto> getAllCustomerPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.customerService.getAllCustomerPaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllCustomerPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllCustomerPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllCustomer")
    public ResponseEntity<ResponseDto> getAllCustomer(){
        return  ResponseEntity.ok(this.customerService.getAllCustomer());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllCustomer() {
        System.out.println("=======fallBackGetAllCustomer=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetCustomerById")
    public ResponseEntity<ResponseDto> getCustomerById(@RequestParam Integer id){

        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetCustomerById(Integer id) {
        System.out.println("=======fallBackGetCustomerById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam Integer id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok(customerService.editCustomer(customerRequestDto));
    }
    @PutMapping("/edit-list")
    public ResponseEntity<ResponseDto> editListCustomer(@RequestBody List<CustomerRequestDto> customerRequestDtoList){
        return ResponseEntity.ok(customerService.editListCustomer(customerRequestDtoList));
    }
}
