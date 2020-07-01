package coffeesystem.controller;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplyContractRequestDto;
import coffeesystem.service.supplyContract.SupplyContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/supply-contract")
public class SupplyContractController {
    @Autowired
    SupplyContractService supplyContractService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplyContract(@RequestBody SupplyContractRequestDto supplyContractRequestDto){
        return ResponseEntity.ok(supplyContractService.createSupplyContract(supplyContractRequestDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllSupplyContractPaging")
    public ResponseEntity<PagingResponseDto> getAllSupplyContractPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
        return ResponseEntity.ok(this.supplyContractService.getAllSupplyContractPaging(page, size, sort, sortColumn));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllSupplyContractPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllSupplyContractPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllSupplyContract")
    public ResponseEntity<ResponseDto> getAllSupplyContract(){
        return  ResponseEntity.ok(this.supplyContractService.getAllSupplyContract());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllSupplyContract() {
        System.out.println("=======fallBackGetAllSupplyContract=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetSupplyContractById")
    public ResponseEntity<ResponseDto> getSupplyContractId(@RequestParam Integer id){
        return ResponseEntity.ok(supplyContractService.getSupplyContractById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetSupplyContractById(Integer id) {
        System.out.println("=======fallBackGetSupplyContractById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all-date-to-date")
//    @HystrixCommand(fallbackMethod = "fallBackGetSupplyContractDateToDate")
    public ResponseEntity<ResponseDto> getAllSupplyContractDateToDate(@RequestParam String fromDate,
                                                                      @RequestParam String toDate){
        return  ResponseEntity.ok(this.supplyContractService.getAllSupplyContractDateToDate(fromDate, toDate));
    }

    public ResponseEntity<ResponseDto> fallBackGetSupplyContractDateToDate(String fromDate, String toDate) {
        System.out.println("=======fallBackGetMaterialDateToDate=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSupplyContract(@RequestParam Integer id){
        return ResponseEntity.ok(supplyContractService.deleteSupplyContract(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplyContract(@RequestBody SupplyContractRequestDto supplyContractRequestDto){
        return ResponseEntity.ok(supplyContractService.editSupplyContract(supplyContractRequestDto));
    }
}
