package com.springboot.angular.coffeesystem.service.employee;

import com.springboot.angular.coffeesystem.dto.*;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Account;
import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.Employee;
import com.springboot.angular.coffeesystem.model.EmployeeType;
import com.springboot.angular.coffeesystem.repository.AccountRepository;
import com.springboot.angular.coffeesystem.repository.BranchShopRepository;
import com.springboot.angular.coffeesystem.repository.EmployeeRepository;
import com.springboot.angular.coffeesystem.repository.EmployeeTypeRepository;
import com.springboot.angular.coffeesystem.service.account.AccountService;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        return new ResponseDto(HttpStatus.OK.value(), "Successful", employeeResponseDto);
    }
    public ResponseDto deleteEmployee(Integer id){
        Employee employee = employeeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        employee.setEnable(false);
        employeeRepository.save(employee);
        return new ResponseDto(HttpStatus.OK.value(), "Delete employee successful", null);
    }
    public ResponseDto editEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = employeeRepository.findByIdAndEnable(employeeRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        employee.setName(employeeRequestDto.getName());
        employee.setEmail(employeeRequestDto.getEmail());
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(employeeRequestDto.getBranchShop(), true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        EmployeeType employeeType = employeeTypeRepository.findByNameAndEnable(employeeRequestDto.getEmployeeType(), true)
                .orElseThrow(()-> new NotFoundException("Employee type not found"));
        employee.setBranchShop(branchShop);
        employee.setEmployeeType(employeeType);
        employeeRepository.save(employee);
        return new ResponseDto(HttpStatus.OK.value(), "Edit employee successful", null);
    }
}
