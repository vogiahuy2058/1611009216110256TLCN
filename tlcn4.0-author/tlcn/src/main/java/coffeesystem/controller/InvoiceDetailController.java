package coffeesystem.controller;

import coffeesystem.dto.InvoiceDetailRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.invoiceDetail.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/invoice-detail")
public class InvoiceDetailController {
    @Autowired
    InvoiceDetailService invoiceDetailService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInvoiceDetail(@RequestBody InvoiceDetailRequestDto invoiceDetailRequestDto){
        return ResponseEntity.ok(invoiceDetailService.createInvoiceDetail(invoiceDetailRequestDto));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInvoiceDetail(@RequestBody InvoiceDetailRequestDto invoiceDetailRequestDto){
        return ResponseEntity.ok(invoiceDetailService.editInvoiceDetail(invoiceDetailRequestDto));
    }

    @PutMapping("/edit-list")
    public ResponseEntity<ResponseDto> editListInvoiceDetail(@RequestBody List<InvoiceDetailRequestDto> invoiceDetailRequestDtoList){
        return ResponseEntity.ok(invoiceDetailService.editListInvoiceDetail(invoiceDetailRequestDtoList));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteInvoiceDetail(@RequestParam Integer invoiceId,
                                                           @RequestParam Integer drinkId,
                                                           @RequestParam Integer id){
        return ResponseEntity.ok(invoiceDetailService.deleteInvoiceDetail(invoiceId, drinkId, id));
    }
    @GetMapping("/get-by-id-invoice")
//    @HystrixCommand(fallbackMethod = "fallBackGetInvoiceDetailByInvoiceId")
    public ResponseEntity<ResponseDto> getInvoiceDetailByInvoiceId(@RequestParam Integer invoiceId){

        return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailByInvoiceId(invoiceId));
    }

    public ResponseEntity<ResponseDto> fallBackGetInvoiceDetailByInvoiceId(Integer invoiceId) {
        System.out.println("=======fallBackGetInvoiceDetailByInvoiceId=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-by-id-invoice-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInvoiceDetailPaging")
    public ResponseEntity<PagingResponseDto> getInvoiceDetailByInvoiceIdPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "invoiceDetailId.id") String sortColumn,
            @RequestParam Integer invoiceId){
        return  ResponseEntity.ok(this.invoiceDetailService.
                getInvoiceDetailByInvoiceIdPaging(page, size, sort, sortColumn, invoiceId));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllInvoiceDetailPaging(int page, int size, String sort,
                                                                               String sortColumn, Integer invoiceId) {
        System.out.println("=======fallBackGetAllInvoiceDetailPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-id-status-invoice")
//    @HystrixCommand(fallbackMethod = "fallBackGetByInvoiceIdAndStatus")
    public ResponseEntity<ResponseDto> getInvoiceDetailByInvoiceIdAndStatusInvoice
            (@RequestParam Integer invoiceId,
             @RequestParam Integer status){

        return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailByInvoiceIdAndStatus(invoiceId, status));
    }
    public ResponseEntity<ResponseDto> fallBackGetByInvoiceIdAndStatus(Integer invoiceId, Integer status) {
        System.out.println("=======fallBackGetByInvoiceIdAndStatus=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-by-id")
//    @HystrixCommand(fallbackMethod = "fallBackGetInvoiceDetailById")
    public ResponseEntity<ResponseDto> getInvoiceDetailById(@RequestParam Integer id){

        return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailByID(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetInvoiceDetailById(Integer id) {
        System.out.println("=======fallBackGetInvoiceDetailById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInvoiceDetailId(){

        return ResponseEntity.ok(invoiceDetailService.getMaxIdInvoiceDetail());
    }
}
