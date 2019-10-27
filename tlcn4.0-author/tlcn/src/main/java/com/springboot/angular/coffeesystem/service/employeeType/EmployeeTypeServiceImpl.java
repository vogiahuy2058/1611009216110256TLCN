package com.springboot.angular.coffeesystem.service.employeeType;

import com.springboot.angular.coffeesystem.dto.EmployeeTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Employee;
import com.springboot.angular.coffeesystem.model.EmployeeType;
import com.springboot.angular.coffeesystem.repository.EmployeeRepository;
import com.springboot.angular.coffeesystem.repository.EmployeeTypeRepository;
import com.springboot.angular.coffeesystem.service.employee.EmployeeService;
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
public class EmployeeTypeServiceImpl implements EmployeeTypeService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    EmployeeTypeRepository employeeTypeRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    public ResponseDto createEmployeeType(EmployeeTypeDto employeeTypeDto){
        EmployeeType employeeType = this.mapperObject.EmployeeDtoToEntity(employeeTypeDto);
        employeeTypeRepository.save(employeeType);
        return new ResponseDto(HttpStatus.OK.value(), "Create employee type successful", null);
    }
    @Transactional
    public ResponseDto getAllEmployeeType(){
        List<EmployeeType> employeeTypes =  employeeTypeRepository.findAllByEnable(true);
        List<EmployeeTypeDto> employeeTypeDtos = new ArrayList<>();
        employeeTypes.forEach(element->{
            EmployeeTypeDto employeeTypeDto = mapperObject.EmployeeTypeEntityToDto(element);
            employeeTypeDtos.add(employeeTypeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All employee type", employeeTypeDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllEmployeeTypePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<EmployeeTypeDto> employeeTypeDtos = new ArrayList<>();
        Page<EmployeeType> employeeTypePage = employeeTypeRepository.findAllByEnable(true, pageable);
        employeeTypePage.forEach(element->{
            EmployeeTypeDto employeeTypeDto = mapperObject.EmployeeTypeEntityToDto(element);
            employeeTypeDtos.add(employeeTypeDto);});
        Page<EmployeeTypeDto> employeeTypeDtoPage = new PageImpl<>(employeeTypeDtos, pageable,
                employeeTypePage.getTotalElements() );
        return new PagingResponseDto<>(
                employeeTypeDtoPage.getContent(), employeeTypeDtoPage.getTotalElements(), employeeTypeDtoPage.getTotalPages(),
                employeeTypeDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getEmployeeTypeById(Integer id){
        EmployeeType employeeType = employeeTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        EmployeeTypeDto employeeTypeDto = mapperObject.EmployeeTypeEntityToDto(employeeType);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", employeeTypeDto);
    }
    public ResponseDto deleteEmployeeType(Integer id){
        EmployeeType employeeType = employeeTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete employee when employee type was deleted
        List<Employee> employees = employeeRepository.findByEmployeeTypeId(id);
        employees.forEach(element->{
            employeeService.deleteEmployee(element.getId());
        });
        employeeType.setEnable(false);
        employeeTypeRepository.save(employeeType);
        return new ResponseDto(HttpStatus.OK.value(),
                "Delete employee type successful", null);
    }
    public ResponseDto editEmployeeType(EmployeeTypeDto employeeTypeDto){
        EmployeeType employeeType = employeeTypeRepository.findByIdAndEnable(employeeTypeDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        employeeType.setName(employeeTypeDto.getName());
        employeeTypeRepository.save(employeeType);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink type successful", null);
    }
}
