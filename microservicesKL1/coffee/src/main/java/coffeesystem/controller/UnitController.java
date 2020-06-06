package coffeesystem.controller;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.UnitDto;
import coffeesystem.service.unit.UnitService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/unit")
public class UnitController {
    @Autowired
    UnitService unitService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUnit(@RequestBody UnitDto unitDto){
        return ResponseEntity.ok(unitService.createUnit(unitDto));
    }
    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllUnitPaging")
    public ResponseEntity<PagingResponseDto> getAllUnitPaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.unitService.getAllUnitPaging(page, size, sort, sortColumn));
    }

    public ResponseEntity<PagingResponseDto> fallBackGetAllUnitPaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllUnitPaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllUnit")
    public ResponseEntity<ResponseDto> getAllUnit(){
        return  ResponseEntity.ok(this.unitService.getAllUnit());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllUnit() {
        System.out.println("=======fallBackGetAllUnit=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetUnitById")
    public ResponseEntity<ResponseDto> getUnitById(@RequestParam Integer id){
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetUnitById(Integer id) {
        System.out.println("=======fallBackGetUnitById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUnit(@RequestParam Integer id){
        return ResponseEntity.ok(unitService.deleteUnit(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editUnit(@RequestBody UnitDto unitDto){
        return ResponseEntity.ok(unitService.editUnit(unitDto));
    }
}
