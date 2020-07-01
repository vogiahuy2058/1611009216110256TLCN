package coffeesystem.controller;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/get-all-paging")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllRolePaging")
    public ResponseEntity<PagingResponseDto> getAllRolePaging(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "name") String sortColumn){
        return ResponseEntity.ok(this.roleService.getAllRolePaging(page, size, sort, sortColumn));
    }
    public ResponseEntity<PagingResponseDto> fallBackGetAllRolePaging(int page, int size, String sort, String sortColumn) {
        System.out.println("=======fallBackGetAllRolePaging=========");
        return new ResponseEntity<PagingResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get-all")
//    @HystrixCommand(fallbackMethod = "fallBackGetAllRole")
    public ResponseEntity<ResponseDto> getAllRole(){
        return  ResponseEntity.ok(this.roleService.getAllRole());
    }
    public ResponseEntity<ResponseDto> fallBackGetAllRole() {
        System.out.println("=======fallBackGetAllRole=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }

    @GetMapping("/get")
//    @HystrixCommand(fallbackMethod = "fallBackGetRoleById")
    public ResponseEntity<ResponseDto> getRoleById(@RequestParam Integer id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    public ResponseEntity<ResponseDto> fallBackGetRoleById(Integer id) {
        System.out.println("=======fallBackGetRoleById=========");
        return new ResponseEntity<ResponseDto>(HttpStatus.OK);
    }
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteRole(@RequestParam Integer id){
        return ResponseEntity.ok(roleService.deleteRole(id));
    }
}
