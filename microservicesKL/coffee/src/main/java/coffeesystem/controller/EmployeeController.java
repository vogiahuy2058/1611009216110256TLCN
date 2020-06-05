package coffeesystem.controller;

import coffeesystem.dto.EmployeeRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.employee.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequestDto));
    }
    @GetMapping("/get-all-paging")
    @HystrixCommand(fallbackMethod = "fallBackGetAllEmployeePaging")
    public ResponseEntity<PagingResponseDto> getAllEmployeePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.employeeService.getAllEmployeePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllEmployeePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllEmployeePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
    @HystrixCommand(fallbackMethod = "fallBackGetAllEmployee")
    public ResponseEntity<ResponseDto> getAllEmployee(){
        return  ResponseEntity.ok(this.employeeService.getAllEmployee());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllEmployee() {
        System.out.println("=======fallBackGetAllEmployee=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-all-not-account")
    public ResponseEntity<ResponseDto> getEmployeeNotHaveAccountByEmployeeType(@RequestParam String nameEmployeeType){
        return  ResponseEntity.ok(
                this.employeeService.getEmployeeNotHaveAccountByEmployeeType(nameEmployeeType));
    }
    @GetMapping("/get-by-branch-shop")
    @HystrixCommand(fallbackMethod = "fallBackGetEmployeeByBranchShopID")
    public ResponseEntity<ResponseDto> getEmployeeByBranchShopId(@RequestParam Integer branchShopId){
        return  ResponseEntity.ok(this.employeeService.getEmployeeByBranchShopId(branchShopId));
    }

    public ResponseEntity<ResponseDto> fallBackGetEmployeeByBranchShopID(Integer branchShopId) {
        System.out.println("=======fallBackGetEmployeeByBranchShopID=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get")
    @HystrixCommand(fallbackMethod = "fallBackGetEmployeeById")
    public ResponseEntity<ResponseDto> getEmployeeById(@RequestParam Integer id){

        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetEmployeeById(Integer id) {
        System.out.println("=======fallBackGetEmployeeById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @GetMapping("/get-username")
    public ResponseEntity<ResponseDto> getEmployeeByUsername(){

        return ResponseEntity.ok(employeeService.getEmployeeByUsername());
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestParam Integer id){
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.editEmployee(employeeRequestDto));
    }
    @PutMapping("/edit-not-employee-type")
    public ResponseEntity<ResponseDto> editEmployeeNotIncludeEmployeeType(@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.editEmployeeNotIncludeEmployeeType(employeeRequestDto));
    }
}
