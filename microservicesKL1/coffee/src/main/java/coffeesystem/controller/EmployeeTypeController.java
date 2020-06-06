package coffeesystem.controller;

import coffeesystem.dto.EmployeeTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.employeeType.EmployeeTypeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee-type")
public class EmployeeTypeController {
    @Autowired
    EmployeeTypeService employeeTypeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployeeType(@RequestBody EmployeeTypeDto employeeTypeDto){
        return ResponseEntity.ok(employeeTypeService.createEmployeeType(employeeTypeDto));
    }
    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllEmployeeTypePaging")
    public ResponseEntity<PagingResponseDto> getAllEmployeeTypePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.employeeTypeService.getAllEmployeeTypePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllEmployeeTypePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllEmployeeTypePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllEmployeeType")
    public ResponseEntity<ResponseDto> getAllEmployeeType(){
        return  ResponseEntity.ok(this.employeeTypeService.getAllEmployeeType());
    }

    public ResponseEntity<ResponseDto> fallBackGetAllEmployeeType() {
        System.out.println("=======fallBackGetAllEmployeeType=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetEmployeeTypeById")
    public ResponseEntity<ResponseDto> getEmployeeTypeId(@RequestParam Integer id){
        return  ResponseEntity.ok(this.employeeTypeService.getEmployeeTypeById(id));
    }
    public ResponseEntity<ResponseDto> fallBackGetEmployeeTypeById(Integer id) {
        System.out.println("=======fallBackGetEmployeeTypeById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxEmployeeType(){

        return ResponseEntity.ok(employeeTypeService.getMaxIdEmployeeType());
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployeeType(@RequestParam Integer id){
        return ResponseEntity.ok(employeeTypeService.deleteEmployeeType(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editEmployeeType(@RequestBody EmployeeTypeDto employeeTypeDto){
        return ResponseEntity.ok(employeeTypeService.editEmployeeType(employeeTypeDto));
    }
}
