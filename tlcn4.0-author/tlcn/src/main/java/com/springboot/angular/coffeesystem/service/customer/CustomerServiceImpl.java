package com.springboot.angular.coffeesystem.service.customer;

import com.springboot.angular.coffeesystem.dto.CustomerRequestDto;
import com.springboot.angular.coffeesystem.dto.CustomerResponseDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.ExistException;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Customer;
import com.springboot.angular.coffeesystem.model.CustomerType;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.repository.CustomerRepository;
import com.springboot.angular.coffeesystem.repository.CustomerTypeRepository;
import com.springboot.angular.coffeesystem.repository.InvoiceRepository;
import com.springboot.angular.coffeesystem.service.invoice.InvoiceService;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerTypeRepository customerTypeRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceService invoiceService;
    public ResponseDto createCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = this.mapperObject.CustomerDtoToEntity(customerRequestDto);
        if(customerRepository.findByPhone(customerRequestDto.getPhone()).isPresent()){
            throw new ExistException("Customer is existed");
        }
        CustomerType customerType = customerTypeRepository.findByNameAndEnable(customerRequestDto.getCustomerType(), true)
                .orElseThrow(()-> new NotFoundException("Customer type not found"));
        customer.setCustomerType(customerType);
        customerRepository.save(customer);
        return new ResponseDto(HttpStatus.OK.value(), "Create customer successful", null);
    }
    @Transactional
    public ResponseDto getAllCustomer(){
        List<Customer> customers = customerRepository.findAllByEnable(true);
        List<CustomerRequestDto> customerRequestDtos = new ArrayList<>();
        customers.forEach(element->{
            CustomerRequestDto customerRequestDto = mapperObject.CustomerEntityToDt1(element);
            customerRequestDto.setCustomerType(element.getCustomerType().getName());
            customerRequestDtos.add(customerRequestDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All customer", customerRequestDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllCustomerPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<CustomerResponseDto> coffeeTableDtos = new ArrayList<>();
        Page<Customer> customerPage = customerRepository.findAllByEnable(true, pageable);
        customerPage.forEach(element->{
            CustomerResponseDto customerResponseDto = mapperObject.CustomerEntityToDto(element);
            customerResponseDto.setCustomerType(element.getCustomerType().getName());
            coffeeTableDtos.add(customerResponseDto);});
        Page<CustomerResponseDto> customerResponseDtos = new PageImpl<>(coffeeTableDtos, pageable, customerPage.getTotalElements() );
        return new PagingResponseDto<>(
                customerResponseDtos.getContent(), customerResponseDtos.getTotalElements(), customerResponseDtos.getTotalPages(),
                customerResponseDtos.getPageable());
    }
    @Transactional
    public ResponseDto getCustomerById(Integer id){
        Customer customer = customerRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        CustomerResponseDto customerResponseDto = mapperObject.CustomerEntityToDto(customer);
        customerResponseDto.setBirthDay(customer.getBirthDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        customerResponseDto.setCustomerType(customer.getCustomerType().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", customerResponseDto);
    }
    public ResponseDto deleteCustomer(Integer id){
        Customer customer = customerRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete invoice when customer was deleted
        List<Invoice> invoices = invoiceRepository.findByCustomerId(id);
        invoices.forEach(element->{
           invoiceService.deleteInvoice(element.getId());
        });
        customer.setEnable(false);
        customerRepository.save(customer);
        return new ResponseDto(HttpStatus.OK.value(), "Delete customer successful", null);
    }
    public ResponseDto editCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = customerRepository.findByIdAndEnable(customerRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        customer.setName(customerRequestDto.getName());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setBirthDay(customerRequestDto.getBirthDay());
        customer.setPhone(customerRequestDto.getPhone());
        customer.setSex(customerRequestDto.isSex());
        CustomerType customerType = customerTypeRepository.findByNameAndEnable(customerRequestDto.getCustomerType(), true)
                .orElseThrow(()-> new NotFoundException("Customer type not found"));
        customer.setCustomerType(customerType);
        customerRepository.save(customer);
        return new ResponseDto(HttpStatus.OK.value(), "Edit customer successful", null);
    }
}
