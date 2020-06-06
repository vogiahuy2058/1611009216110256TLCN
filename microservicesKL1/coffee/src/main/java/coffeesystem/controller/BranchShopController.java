package coffeesystem.controller;

import coffeesystem.dto.BranchShopDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.branchShop.BranchShopService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/branch-shop")
public class BranchShopController {
    @Autowired
    BranchShopService branchShopService;
    @Autowired
    private RestTemplate template;

//    @HystrixCommand(fallbackMethod = "recommendationFallback")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createBranchShop(@RequestBody BranchShopDto branchShopDto){
//        template.postForObject("http://localhost:8088/api/v1/branch-shop/create", branchShopDto, BranchShopDto.class);
        return ResponseEntity.ok(branchShopService.createBranchShop(branchShopDto));
    }

    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllBranchShopPaging")
    public ResponseEntity<PagingResponseDto> getAllBranchPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.branchShopService.getAllBranchShopPaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllBranchShopPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllBranchShopPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllBranchShop")
    public ResponseEntity<ResponseDto> getAllBranchShop(){
        return  ResponseEntity.ok(this.branchShopService.getAllBranchShop());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllBranchShop() {
        System.out.println("=======fallBackGetAllBranchShop=========");
//        return ResponseEntity.ok(branchShopService.hystrixBranchShop());
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetBranchShopById")
    public ResponseEntity<ResponseDto> getBranchShopById(@RequestParam Integer id){
        return ResponseEntity.ok(branchShopService.getBranchShopById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetBranchShopById(Integer id) {
        System.out.println("=======fallBackGetBranchShopById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
//    @HystrixCommand(fallbackMethod = "fallBackDeleteBranchShop")
    public ResponseEntity<ResponseDto> deleteBranchShop(@RequestParam Integer id){
        return ResponseEntity.ok(branchShopService.deleteBranchShop(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editBranchShop(@RequestBody BranchShopDto branchShopDto){
        return ResponseEntity.ok(branchShopService.editBranchShop(branchShopDto));
    }
}
