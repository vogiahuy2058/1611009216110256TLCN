package coffeesystem.controller;

import coffeesystem.dto.OrderTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.orderType.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/order-type")
public class OrderTypeController {
    @Autowired
    OrderTypeService orderTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrderType(@RequestBody OrderTypeDto orderTypeDto){
        return ResponseEntity.ok(orderTypeService.createOrderType(orderTypeDto));
    }
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllOrderTypePaging")
    public ResponseEntity<PagingResponseDto> getAllOrderTypePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.orderTypeService.getAllOrderTypePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllOrderTypePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllOrderTypePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllOrderType")
    public ResponseEntity<ResponseDto> getAllOrderType(){
        return  ResponseEntity.ok(this.orderTypeService.getAllOrderType());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllOrderType() {
        System.out.println("=======fallBackGetAllOrderType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetOrderTypeById")
    public ResponseEntity<ResponseDto> getOrderTypeById(@RequestParam Integer id){
        return ResponseEntity.ok(orderTypeService.getOrderTypeById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetOrderTypeById(Integer id) {
        System.out.println("=======fallBackGetOrderTypeById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteOrderType(@RequestParam Integer id){
        return ResponseEntity.ok(orderTypeService.deleteOrderType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editOrderType(@RequestBody OrderTypeDto orderTypeDto){
        return ResponseEntity.ok(orderTypeService.editOrderType(orderTypeDto));
    }
}
