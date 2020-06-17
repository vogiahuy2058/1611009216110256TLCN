package coffeesystem.service.employee;


import coffeesystem.dto.EmployeeRequestDto;
import coffeesystem.dto.EmployeeResponseDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Account;
import coffeesystem.model.BranchShop;
import coffeesystem.model.Employee;
import coffeesystem.model.EmployeeType;
import coffeesystem.repository.AccountRepository;
import coffeesystem.repository.BranchShopRepository;
import coffeesystem.repository.EmployeeRepository;
import coffeesystem.repository.EmployeeTypeRepository;
import coffeesystem.service.account.AccountService;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    @Autowired
    EmployeeTypeRepository employeeTypeRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @Transactional
    public ResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = this.mapperObject.EmployeeDtoToEntity(employeeRequestDto);
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(employeeRequestDto.getBranchShop(), true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        EmployeeType employeeType = employeeTypeRepository.findByNameAndEnable(employeeRequestDto.getEmployeeType(), true)
                .orElseThrow(()-> new NotFoundException("Employee type not found"));
        employee.setBranchShop(branchShop);
        employee.setEmployeeType(employeeType);
        employeeRepository.save(employee);
        return new ResponseDto(HttpStatus.OK.value(), "create employee successful", null);
    }
    @Transactional
    public ResponseDto getAllEmployee(){
        List<Employee> employeeList = employeeRepository.findAllByEnable(true);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        employeeList.forEach(employee -> {
            EmployeeResponseDto employeeResponseDto = mapperObject.EmployeeEntityToDto(employee);
            employeeResponseDto.setBranchShop(employee.getBranchShop().getName());
            employeeResponseDto.setEmployeeType(employee.getEmployeeType().getName());
            employeeResponseDtos.add(employeeResponseDto);
        });

        return new ResponseDto(HttpStatus.OK.value(), "All employee",employeeResponseDtos);
    }

    @Transactional
    @Override
    public PagingResponseDto getAllEmployeePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        Page<Employee> employeePage = employeeRepository.findAllByEnable(true, pageable);
        employeePage.forEach(element-> {
            EmployeeResponseDto employeeResponseDto = mapperObject.EmployeeEntityToDto(element);
            employeeResponseDto.setBranchShop(element.getBranchShop().getName());
            employeeResponseDto.setEmployeeType(element.getEmployeeType().getName());
            employeeResponseDtos.add(employeeResponseDto);
        });
        Page<EmployeeResponseDto> employeeResponseDtoPage = new PageImpl<>(employeeResponseDtos, pageable,
                employeePage.getTotalElements() );

        return new PagingResponseDto<>(
                employeeResponseDtoPage.getContent(), employeeResponseDtoPage.getTotalElements(), employeeResponseDtoPage.getTotalPages(),
                employeeResponseDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getEmployeeById(Integer id){
        Employee employee = employeeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        EmployeeResponseDto employeeResponseDto = mapperObject.EmployeeEntityToDto(employee);
        employeeResponseDto.setEmployeeType(employee.getEmployeeType().getName());
        employeeResponseDto.setBranchShop(employee.getBranchShop().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", employeeResponseDto);
    }
    @Transactional
    public ResponseDto getEmployeeByUsername(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByAccountUsername(username)
                .orElseThrow(()-> new NotFoundException("Username not found"));
        EmployeeResponseDto employeeResponseDto = mapperObject.EmployeeEntityToDto(employee);
        employeeResponseDto.setEmployeeType(employee.getEmployeeType().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", employeeResponseDto);
    }
    @Transactional
    public ResponseDto getEmployeeNotHaveAccountByEmployeeType(String nameEmployeeType){
        EmployeeType employeeType = employeeTypeRepository.findByNameAndEnable(nameEmployeeType, true)
                .orElseThrow(()-> new NotFoundException("Employee type not found"));
        List<Employee> employeeList = employeeRepository.findEmployeeNotHaveAccountByEmployeeType(nameEmployeeType);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        employeeList.forEach(employee -> {
            EmployeeResponseDto employeeResponseDto = mapperObject.EmployeeEntityToDto(employee);
            employeeResponseDto.setBranchShop(employee.getBranchShop().getName());
            employeeResponseDto.setEmployeeType(employee.getEmployeeType().getName());
            employeeResponseDtos.add(employeeResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(),
            "All employee don't have account",employeeResponseDtos);
    }
    @Transactional
    public ResponseDto getEmployeeByBranchShopId(Integer branchShopId){
        BranchShop branchShop = branchShopRepository.findByIdAndEnable(branchShopId, true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        List<Employee> employees = employeeRepository.findByBranchShopIdAndEnable(branchShopId, true);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeResponseDto employeeResponseDto = mapperObject.EmployeeEntityToDto(employee);
            employeeResponseDto.setBranchShop(employee.getBranchShop().getName());
            employeeResponseDto.setEmployeeType(employee.getEmployeeType().getName());
            employeeResponseDtos.add(employeeResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(),
                "List employee by branch shop id",employeeResponseDtos);
    }
    public ResponseDto deleteEmployee(Integer id){
        Employee employee = employeeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete account when employee  was deleted
        List<Account> accounts = accountRepository.findByEmployeeId(id);
        accounts.forEach(element->{
            accountService.deleteAccount(element.getId());
        });
        employee.setEnable(false);
        employeeRepository.save(employee);
        return new ResponseDto(HttpStatus.OK.value(), "Delete employee successful", null);
    }
    public ResponseDto editEmployeeNotIncludeEmployeeType(EmployeeRequestDto employeeRequestDto){
        Employee employee = employeeRepository.findByIdAndEnable(employeeRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        employee.setName(employeeRequestDto.getName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setIdCuaHuy(employeeRequestDto.getIdCuaHuy());
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(employeeRequestDto.getBranchShop(), true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
//        EmployeeType employeeType = employeeTypeRepository.findByNameAndEnable(employeeRequestDto.getEmployeeType(), true)
//                .orElseThrow(()-> new NotFoundException("Employee type not found"));
        employee.setBranchShop(branchShop);
//        employee.setEmployeeType(employeeType);
        employeeRepository.save(employee);
        return new ResponseDto(HttpStatus.OK.value(), "Edit employee successful", null);
    }
    public ResponseDto editEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = employeeRepository.findByIdAndEnable(employeeRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        employee.setName(employeeRequestDto.getName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setIdCuaHuy(employeeRequestDto.getIdCuaHuy());
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(employeeRequestDto.getBranchShop(), true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        EmployeeType employeeType = employeeTypeRepository.findByNameAndEnable(employeeRequestDto.getEmployeeType(), true)
                .orElseThrow(()-> new NotFoundException("Employee type not found"));
        employee.setBranchShop(branchShop);
        employee.setEmployeeType(employeeType);
        employeeRepository.save(employee);
        List<Account> accounts = accountRepository.findByEmployeeId(employee.getId());
        accounts.forEach(element->{
            //khi edit nhan vien ma co edit ca loai nhan vien thì status edit =false
            element.setStatusEdit(false);
            accountService.deleteAccount(element.getId());
        });
        return new ResponseDto(HttpStatus.OK.value(), "Edit employee successful", null);
    }
    @Transactional
    public ResponseDto getMaxIdEmployee(){
        Integer maxId = employeeRepository.findMaxId();
        if(maxId == null){
            maxId = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", maxId);

    }
}
