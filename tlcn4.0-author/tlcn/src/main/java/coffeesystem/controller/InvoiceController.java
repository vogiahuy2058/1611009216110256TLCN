package coffeesystem.controller;

import coffeesystem.dto.InvoiceRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceRequestDto));
    }
//    @GetMapping("/get-all")
//    public ResponseEntity<PagingResponseDto> getAllInvoicePaging(
//            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
//        return ResponseEntity.ok(this.invoiceService.getAllInvoicePaging(page, size, sort, sortColumn));
//    }

//    @GetMapping("/get-all-true")
//    public ResponseEntity<PagingResponseDto> getAllInvoiceStatusTruePaging(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
//            @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
//        return  ResponseEntity.ok(this.invoiceService.getAllInvoicePagingStatusTrue(page, size, sort, sortColumn));
//    }

    @GetMapping("/get-all-true-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInvoicePaging")
    public ResponseEntity<PagingResponseDto> getAllInvoiceStatusTrue(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                                     @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                                                                     @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus2Paging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllInvoicePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllInvoicePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all-true-id-branch-shop-paging")
    public ResponseEntity<PagingResponseDto> getAllInvoiceStatusTrueByBranchShopId(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                                     @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                                                                     @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn,
                                                                                   @RequestParam Integer idBranchShop){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus2PagingByBranchShop(page, size, sort, sortColumn, idBranchShop));
    }

    @GetMapping("/get-all-by-status")
//    @HystrixCommand(fallbackMethod = "fallBackGetInvoiceByStatus")
    public ResponseEntity<ResponseDto> getAllInvoiceByStatus(@RequestParam Integer status){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceByStatus(status));
    }
    public ResponseEntity<ResponseDto> fallBackGetInvoiceByStatus(Integer status) {
        System.out.println("=======fallBackGetInvoiceByStatus=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all-by-status-and-id-branch-shop")
    public ResponseEntity<ResponseDto> getAllInvoiceByStatusAndIdBranchShop(@RequestParam Integer status,
                                                                            @RequestParam Integer idBranchShop){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceByStatusAndIdBranchShop(status, idBranchShop));
    }
    @GetMapping("/get-all-date-to-date-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInvoiceDateToDatePaging")
    public ResponseEntity<PagingResponseDto> getAllInvoiceDateToDatePaging(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                               @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                                                               @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn,
                                                               @RequestParam String fromDate,
                                                               @RequestParam String toDate,
                                                               @RequestParam Integer branchShopId){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceByFilterPaging(page, size, sort, sortColumn, fromDate, toDate, branchShopId));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllInvoiceDateToDatePaging(
            int page, int size, String sort, String sortColumn, String fromDate, String toDate, Integer branchShopId) {
        System.out.println("=======fallBackGetAllInvoiceDateToDatePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all-date-to-date")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInvoiceDateToDate")
    public ResponseEntity<ResponseDto> getAllInvoiceDateToDate(@RequestParam String fromDate,
                                                               @RequestParam String toDate,
                                                               @RequestParam Integer branchShopId){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceByFilter(fromDate, toDate, branchShopId));
    }
    public ResponseEntity<ResponseDto> fallBackGetAllInvoiceDateToDate(String fromDate, String toDate, Integer branchShopId) {
        System.out.println("=======fallBackGetAllInvoiceDateToDatePaging=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all-false")
    public ResponseEntity<ResponseDto> getAllInvoiceStatusFalse(){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus0());
    }
    @GetMapping("/get-all-false-by-branch-shop-id")
    public ResponseEntity<ResponseDto> getAllInvoiceStatusFalse(@RequestParam Integer idBranchShop){
        return  ResponseEntity.ok(this.invoiceService.getAllInvoiceStatus0ByBranchShop(idBranchShop));
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetInvoiceById")
    public ResponseEntity<ResponseDto> getInvoiceById(@RequestParam Integer id){

        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetInvoiceById(Integer id) {
        System.out.println("=======fallBackGetInvoiceById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-full-invoice")
//    @HystrixCommand(fallbackMethod = "fallBackGetFullInvoice")
    public ResponseEntity<ResponseDto> getFullInvoiceById(@RequestParam Integer invoiceId){

        return ResponseEntity.ok(invoiceService.getFullInvoiceById(invoiceId));
    }
    public ResponseEntity<ResponseDto> fallBackGetFullInvoice(Integer invoiceId) {
        System.out.println("=======fallBackGetFullInvoice=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-sales-statistics-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllInvoiceDateToDatePaging")
    public ResponseEntity<PagingResponseDto> getSalesStatisticsPaging(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                                      @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                                                      @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
                                                                      @RequestParam(name = "column", required = false, defaultValue = "id") String sortColumn,
                                                                      @RequestParam String fromDate,
                                                                      @RequestParam String toDate){
        return  ResponseEntity.ok(this.invoiceService.getSalesStatistics(page, size, sort, sortColumn, fromDate, toDate));
    }

    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInvoiceId(){

        return ResponseEntity.ok(invoiceService.getMaxIdInvoice());
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return ResponseEntity.ok(invoiceService.editInvoice(invoiceRequestDto));
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteInvoice(@RequestParam Integer id){
        return ResponseEntity.ok(invoiceService.deleteInvoice(id));
    }

    @PutMapping("/delete-status-false")
    public ResponseEntity<ResponseDto> deleteInvoiceStatusFalse(){
        return ResponseEntity.ok(invoiceService.deleteInvoiceStatus0());
    }
    @PutMapping("/update-amount-material-used")
    public ResponseEntity<ResponseDto> updateAmountMaterialUse(@RequestParam Integer idInvoice){
        return ResponseEntity.ok(invoiceService.updateAmountMaterialUsed(idInvoice));
    }
}
