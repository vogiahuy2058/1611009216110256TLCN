package com.springboot.angular.coffeesystem.service.customerType;

import com.springboot.angular.coffeesystem.dto.CustomerTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Customer;
import com.springboot.angular.coffeesystem.model.CustomerType;
import com.springboot.angular.coffeesystem.repository.CustomerRepository;
import com.springboot.angular.coffeesystem.repository.CustomerTypeRepository;
import com.springboot.angular.coffeesystem.service.customer.CustomerService;
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
public class CustomerTypeServiceImpl implements CustomerTypeService{
    @Autowired
    CustomerTypeRepository customerTypeRepository;
    @Autowired
    MapperObject mapperObject;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    public ResponseDto createCustomerType(CustomerTypeDto customerTypeDto){
        CustomerType customerType = this.mapperObject.CustomerTypeDtoToEntity(customerTypeDto);
        customerTypeRepository.save(customerType);
        return new ResponseDto(HttpStatus.OK.value(), "create customer type successful", null);
    }
    @Transactional
    public ResponseDto getAllCustomerType(){
        List<CustomerType> customerTypes = customerTypeRepository.findAllByEnable(true);
        List<CustomerTypeDto> customerTypeDtos = new ArrayList<>();
        customerTypes.forEach(element -> {
            CustomerTypeDto employeeResponseDto = mapperObject.CustomerTypeEntityToDto(element);
            customerTypeDtos.add(employeeResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All customer type", customerTypeDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllCustomerTypePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<CustomerTypeDto> customerTypeDtos = new ArrayList<>();
        Page<CustomerType> customerTypePage = customerTypeRepository.findAllByEnable(true, pageable);
        customerTypePage.forEach(element->{
            CustomerTypeDto employeeResponseDto = mapperObject.CustomerTypeEntityToDto(element);
            customerTypeDtos.add(employeeResponseDto);});
        Page<CustomerTypeDto> customerTypeDtoPage = new PageImpl<>(customerTypeDtos, pageable,
                customerTypePage.getTotalElements() );
        return new PagingResponseDto<>(
                customerTypeDtoPage.getContent(), customerTypeDtoPage.getTotalElements(), customerTypeDtoPage.getTotalPages(),
                customerTypeDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getCustomerTypeById(Integer id){
        CustomerType customerType = customerTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        CustomerTypeDto customerTypeDto = mapperObject.CustomerTypeEntityToDto(customerType);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", customerTypeDto);
    }
    public ResponseDto editCustomerType(CustomerTypeDto customerTypeDto){
        CustomerType customerType = customerTypeRepository.findByIdAndEnable(customerTypeDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        customerType.setName(customerTypeDto.getName());
        customerTypeRepository.save(customerType);
        return new ResponseDto(HttpStatus.OK.value(), "Edit customer successful", null);
    }
    public ResponseDto deleteCustomerType(Integer id)
    {
        CustomerType customerType = customerTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete customer when customer type was deleted
        List<Customer> customers = customerRepository.findByCustomerTypeId(id);
        customers.forEach(element->{
            customerService.deleteCustomer(element.getId());
        });
        customerType.setEnable(false);
        customerTypeRepository.save(customerType);
        return new ResponseDto(HttpStatus.OK.value(), "Delete customer type successful", null);
    }
}
