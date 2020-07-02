package coffeesystem.controller;

import coffeesystem.dto.InternalSCRequestDto;
import coffeesystem.dto.InternalSCRequestDto1;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.InternalSC.InternalSCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/internal-sc")
@Transactional
public class InternalSCController {
    @Autowired
    InternalSCService internalSCService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInternalSC(@RequestBody InternalSCRequestDto internalSCRequestDto){
        return ResponseEntity.ok(internalSCService.createInternalSC(internalSCRequestDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInternalSCPaging")
    public ResponseEntity<PagingResponseDto> getAllInternalSCPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
        return ResponseEntity.ok(this.internalSCService.getAllInternalSCPaging(page, size, sort, sortColumn));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllInternalSCPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllInternalSCPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInternalSC")
    public ResponseEntity<ResponseDto> getAllInternalSC(){
        return  ResponseEntity.ok(this.internalSCService.getAllInternalSC());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllInternalSC() {
        System.out.println("=======fallBackGetAllInternalSC=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCById")
    public ResponseEntity<ResponseDto> getInternalSCById(@RequestParam Integer id){
        return ResponseEntity.ok(internalSCService.getInternalSCById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetInternalSCById(Integer id) {
        System.out.println("=======fallBackGetInternalSCById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-max-id-by-bs-and-status")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCById")
    public ResponseEntity<ResponseDto> getInternalSCHaveMaxIdByIdBranchShopAndStatus(
            @RequestParam Integer idBranchShop,
            @RequestParam Integer status){
        return ResponseEntity.ok(internalSCService.getInternalSCHaveMaxIdByIdBranchShopAndStatus(idBranchShop, status));
    }
    @GetMapping("/get-all-date-to-date")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCDateToDate")
    public ResponseEntity<ResponseDto> getAllInternalSCDateToDate(@RequestParam String fromDate,
                                                                      @RequestParam String toDate){
        return  ResponseEntity.ok(this.internalSCService.getAllInternalSCDateToDate(fromDate, toDate));
    }
    @GetMapping("/get-by-id-branch-shop-and-status")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCDateToDate")
    public ResponseEntity<ResponseDto> getByIdBranchShopAndStatus(@RequestParam Integer idBranchShop,
                                                                  @RequestParam Integer status){
        return  ResponseEntity.ok(this.internalSCService
                .getAllInternalSCByBranchShopAndStatus(idBranchShop, status));
    }

    public ResponseEntity<ResponseDto> fallBackGetInternalSCDateToDate(String fromDate, String toDate) {
        System.out.println("=======fallBackGetInternalSCDateToDate=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PostMapping("/get-total-number-of-request-by-list-iscd")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCById")
    public ResponseEntity<ResponseDto> getTotalNumberOfRequestMaterial(@RequestBody List<InternalSCRequestDto1> internalSCRequestDto1s){
        return ResponseEntity.ok(internalSCService.getTotalNumberOfRequestAndTotalQuantityAllowMaterial(internalSCRequestDto1s));
    }
    @GetMapping("/get-branch-shop-status1-date-create-now")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCDateToDate")
    public ResponseEntity<ResponseDto> getBranchShopExistInInternalSC(){
        return  ResponseEntity.ok(this.internalSCService.getBranchShopExistInInternalSC());
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSInternalSC(@RequestParam Integer id){
        return ResponseEntity.ok(internalSCService.deleteInternalSC(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInternalSC(@RequestBody InternalSCRequestDto internalSCRequestDto){
        return ResponseEntity.ok(internalSCService.editInternalSC(internalSCRequestDto));
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInternalSCId(){

        return ResponseEntity.ok(internalSCService.getMaxIdInternalSC());
    }
}
