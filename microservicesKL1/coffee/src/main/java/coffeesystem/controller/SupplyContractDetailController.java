package coffeesystem.controller;


import coffeesystem.dto.InvoiceDetailRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplyContractDetailRequestDto;
import coffeesystem.service.supplyContractDetail.SupplyContractDetailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/supply-contract-detail")
public class SupplyContractDetailController {
    @Autowired
    SupplyContractDetailService detailService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplyContractDetail(@RequestBody SupplyContractDetailRequestDto supplyContractDetailRequestDto){
        return ResponseEntity.ok(detailService.createSupplyContractDetail(supplyContractDetailRequestDto));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplyContractDetail(@RequestBody SupplyContractDetailRequestDto supplyContractDetailRequestDto){
        return ResponseEntity.ok(detailService.editSupplyContractDetail(supplyContractDetailRequestDto));
    }
    @PutMapping("/edit-list")
    public ResponseEntity<ResponseDto> editListSupplyContractDetail(
            @RequestBody List<SupplyContractDetailRequestDto> supplyContractDetailRequestDtoList){
        return ResponseEntity.ok(detailService.editListSupplyContractDetail(supplyContractDetailRequestDtoList));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSupplyContractDetail(@RequestParam Integer supplyContractId,
                                                           @RequestParam Integer materialId,
                                                           @RequestParam Integer id){
        return ResponseEntity.ok(detailService.deleteSupplyContractDetail(supplyContractId, materialId, id));
    }
    @GetMapping("/get-by-id-supplycontract")
    @HystrixCommand(fallbackMethod = "fallBackGetSupplyContractDetailBySupplyContractId")
    public ResponseEntity<ResponseDto> getSupplyContractDetailBySupplyContractId(@RequestParam Integer supplyContractId){

        return ResponseEntity.ok(detailService.getSupplyContractDetailBySupplyContractId(supplyContractId));
    }

    public ResponseEntity<ResponseDto> fallBackGetSupplyContractDetailBySupplyContractId(Integer supplyContractId) {
        System.out.println("=======fallBackGetSupplyContractDetailBySupplyContractId=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-by-id-supplycontract-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllSupplyContractDetailPaging")
    public ResponseEntity<PagingResponseDto> getSupplyContractDetailBySupplyContractIdPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "supplyContractDetailId.id") String sortColumn,
            @RequestParam Integer supplyContractId){
        return  ResponseEntity.ok(this.detailService.
                getSupplyContractDetailBySupplyContractIdPaging(page, size, sort, sortColumn, supplyContractId));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllSupplyContractDetailPaging(int page, int size, String sort,
                                                                               String sortColumn, Integer supplyContractId) {
        System.out.println("=======fallBackGetAllSupplyContractDetailPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-id-status-supplycontract")
    @HystrixCommand(fallbackMethod = "fallBackGetBySupplyContractIdAndStatus")
    public ResponseEntity<ResponseDto> getSupplyContractDetailBySupplyContractIdAndStatus
            (@RequestParam Integer supplyContractId,
             @RequestParam Integer status){

        return ResponseEntity.ok(detailService.getSupplyContractDetailBySupplyContractIdAndStatus(supplyContractId, status));
    }
    public ResponseEntity<ResponseDto> fallBackGetBySupplyContractIdAndStatus(Integer supplyContractId, Integer status) {
        System.out.println("=======fallBackGetBySupplyContractIdAndStatus=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-id")
    @HystrixCommand(fallbackMethod = "fallBackGetSupplyContractDetailById")
    public ResponseEntity<ResponseDto> getSupplyContractDetailById(@RequestParam Integer id){

        return ResponseEntity.ok(detailService.getSupplyContractDetailByID(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetSupplyContractDetailById(Integer id) {
        System.out.println("=======fallBackGetSupplyContractDetailById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxSupplyContractDetailId(){

        return ResponseEntity.ok(detailService.getMaxIdSupplyContractDetail());
    }
}
