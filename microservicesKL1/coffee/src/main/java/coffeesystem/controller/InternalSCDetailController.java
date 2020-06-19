package coffeesystem.controller;

import coffeesystem.dto.*;
import coffeesystem.service.internalSCDetail.InternalSCDetailService;
import coffeesystem.service.supplyContractDetail.SupplyContractDetailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/internal-sc-detail")
public class InternalSCDetailController {
    @Autowired
    InternalSCDetailService internalSCDetailService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInternalSCDetail(@RequestBody InternalSCDetailRequestDto internalSCDetailRequestDto){
        return ResponseEntity.ok(internalSCDetailService.createInternalSCDetail(internalSCDetailRequestDto));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInternalSCDetail(@RequestBody InternalSCDetailRequestDto internalSCDetailRequestDto){
        return ResponseEntity.ok(internalSCDetailService.editInternalSCDetail(internalSCDetailRequestDto));
    }
    @PutMapping("/edit-list")
    public ResponseEntity<ResponseDto> editListInternalSCDetail(
            @RequestBody List<InternalSCDetailRequestDto> internalSCDetailRequestDtos){
        return ResponseEntity.ok(internalSCDetailService.editListInternalSCDetail(internalSCDetailRequestDtos));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteSInternalSCDetail(@RequestParam Integer internalSCId,
                                                                  @RequestParam Integer materialId,
                                                                  @RequestParam Integer id){
        return ResponseEntity.ok(internalSCDetailService.deleteInternalSCDetail(internalSCId, materialId, id));
    }
    @GetMapping("/get-by-id-supplycontract")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCDetailByInternalSCId")
    public ResponseEntity<ResponseDto> getInternalSCDetailByInternalSCId(@RequestParam Integer internalSCId){

        return ResponseEntity.ok(internalSCDetailService.getInternalSCDetailByInternalSCId(internalSCId));
    }

    public ResponseEntity<ResponseDto> fallBackGetSupplyContractDetailBySupplyContractId(Integer internalSCId) {
        System.out.println("=======fallBackGetSupplyContractDetailBySupplyContractId=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-by-id-supplycontract-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInternalSCDetailPaging")
    public ResponseEntity<PagingResponseDto> getInternalSCDetailBySInternalSCIdPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "internalSCDetailId.id") String sortColumn,
            @RequestParam Integer internalSCId){
        return  ResponseEntity.ok(this.internalSCDetailService.
                getInternalSCDetailByInternalSCPaging(page, size, sort, sortColumn, internalSCId));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllSupplyContractDetailPaging(int page, int size, String sort,
                                                                                      String sortColumn, Integer internalSCId) {
        System.out.println("=======fallBackGetAllSupplyContractDetailPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-id-status-supplycontract")
//    @HystrixCommand(fallbackMethod = "fallBackGetByInternalSCIdAndStatus")
    public ResponseEntity<ResponseDto> getInternalSCDetailByInternalSCIdAndStatus
            (@RequestParam Integer internalSCId,
             @RequestParam Integer status){

        return ResponseEntity.ok(internalSCDetailService.
                getInternalSCDetailByInternalSCIdAndStatus(internalSCId, status));
    }
    public ResponseEntity<ResponseDto> fallBackGetBySupplyContractIdAndStatus(Integer internalSCId, Integer status) {
        System.out.println("=======fallBackGetBySupplyContractIdAndStatus=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-id")
//    @HystrixCommand(fallbackMethod = "fallBackGetInternalSCDetailById")
    public ResponseEntity<ResponseDto> getInternalSCDetailById(@RequestParam Integer id){

        return ResponseEntity.ok(internalSCDetailService.getInternalSCDetailByID(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetSupplyContractDetailById(Integer id) {
        System.out.println("=======fallBackGetSupplyContractDetailById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInternalSCDetailId(){

        return ResponseEntity.ok(internalSCDetailService.getMaxIdInternalSCDetail());
    }
}
