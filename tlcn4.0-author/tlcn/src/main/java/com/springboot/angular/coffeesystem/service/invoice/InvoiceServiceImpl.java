package com.springboot.angular.coffeesystem.service.invoice;

import com.springboot.angular.coffeesystem.dto.InvoiceRequestDto;
import com.springboot.angular.coffeesystem.dto.InvoiceResponseDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.*;
import com.springboot.angular.coffeesystem.repository.*;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CoffeeTableRepository coffeeTableRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    @Autowired
    OrderTypeRepository orderTypeRepository;
    ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
    public ResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto){
        Invoice invoice = this.mapperObject.InvoiceDtoToEntity(invoiceRequestDto);
        Customer customer = new Customer();
        if(invoiceRequestDto.getCustomerPhone() != null)
        {
            customer = customerRepository.findByPhone(invoiceRequestDto.getCustomerPhone())
                    .orElseThrow(()-> new NotFoundException("Customer not found"));
        }else {
            customer = null;
        }

//        CoffeeTable coffeeTable = new CoffeeTable();
//        if(invoiceRequestDto.getCoffeeTable() != null){
//            coffeeTable = coffeeTableRepository.findByName(invoiceRequestDto.getCoffeeTable())
//                    .orElseThrow(()-> new NotFoundException("Table not found"));
//        }
//        else coffeeTable = null;
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(invoiceRequestDto.getBranchShop(), true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        OrderType orderType = orderTypeRepository.findByNameAndEnable(invoiceRequestDto.getOrderType(), true)
                .orElseThrow(()-> new NotFoundException("Order type not found"));
        invoice.setCustomer(customer);
//        invoice.setCoffeeTable(coffeeTable);
        invoice.setBranchShop(branchShop);
        invoice.setOrderType(orderType);
        invoice.setDate(ZonedDateTime.parse(
                invoiceRequestDto.getDate().withZoneSameInstant(zoneId).toString()));
        invoiceRepository.save(invoice);
        return new ResponseDto(HttpStatus.OK.value(), "Create invoice successful", null);
    }
    @Transactional
    public ResponseDto getAllInvoice(){
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        invoices.forEach(invoice -> {
            InvoiceResponseDto invoiceResponseDto = mapperObject.InvoiceEntityToDto(invoice);

//            if(invoice.getCoffeeTable()==null){
//                invoiceResponseDto.setCoffeeTable(null);
//            }else {
//                invoiceResponseDto.setCoffeeTable(invoice.getCoffeeTable().getName());
//            }

            if(invoice.getCustomer() == null){
                invoiceResponseDto.setCustomerName(null);
                invoiceResponseDto.setCustomerPhone(null);
            }else {
                invoiceResponseDto.setCustomerName(invoice.getCustomer().getName());
                invoiceResponseDto.setCustomerPhone(invoice.getCustomer().getPhone());
            }
            invoiceResponseDto.setBranchShop(invoice.getBranchShop().getName());
            invoiceResponseDto.setOrderType(invoice.getOrderType().getName());
            invoiceResponseDto.setDate(invoice.getDate().
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        Page<Invoice> invoicePage = invoiceRepository.findAllByEnable(true, pageable);
        invoicePage.forEach(element->{
            InvoiceResponseDto invoiceResponseDto = mapperObject.InvoiceEntityToDto(element);

            if(element.getCustomer() == null){
                invoiceResponseDto.setCustomerName(null);
                invoiceResponseDto.setCustomerPhone(null);
            }else {
                invoiceResponseDto.setCustomerName(element.getCustomer().getName());
                invoiceResponseDto.setCustomerPhone(element.getCustomer().getPhone());
            }
//            invoiceResponseDto.setCoffeeTable(element.getCoffeeTable().getName());
            invoiceResponseDto.setBranchShop(element.getBranchShop().getName());
            invoiceResponseDto.setOrderType(element.getOrderType().getName());
            invoiceResponseDto.setDate(element.getDate().
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            invoiceResponseDtos.add(invoiceResponseDto);});
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements() );
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getInvoiceById(Integer id){
        Invoice invoice = invoiceRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        InvoiceResponseDto invoiceResponseDto = mapperObject.InvoiceEntityToDto(invoice);

        if(invoice.getCustomer() == null){
            invoiceResponseDto.setCustomerName(null);
            invoiceResponseDto.setCustomerPhone(null);
        }else {
            invoiceResponseDto.setCustomerName(invoice.getCustomer().getName());
            invoiceResponseDto.setCustomerPhone(invoice.getCustomer().getPhone());
        }
//        invoiceResponseDto.setCoffeeTable(invoice.getCoffeeTable().getName());
        invoiceResponseDto.setBranchShop(invoice.getBranchShop().getName());
        invoiceResponseDto.setOrderType(invoice.getOrderType().getName());
//        invoiceResponseDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm:ss Z").format(invoice.getDate()));
        invoiceResponseDto.setDate(invoice.getDate().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDto );
    }
    public ResponseDto editInvoice(InvoiceRequestDto invoiceRequestDto){
        Invoice invoice = invoiceRepository.findByIdAndEnable(invoiceRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        Customer customer = new Customer();
        if(invoiceRequestDto.getCustomerPhone() != null)
        {
            customer = customerRepository.findByPhone(invoiceRequestDto.getCustomerPhone())
                    .orElseThrow(()-> new NotFoundException("Customer not found"));
        }else {
            customer = null;
        }
//        CoffeeTable coffeeTable = coffeeTableRepository.findByName(invoiceRequestDto.getCoffeeTable())
//                .orElseThrow(()-> new NotFoundException("Table not found"));
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(invoiceRequestDto.getBranchShop(), true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        OrderType orderType = orderTypeRepository.findByNameAndEnable(invoiceRequestDto.getOrderType(), true)
                .orElseThrow(()-> new NotFoundException("Order type not found"));
        invoice.setCustomer(customer);
//        invoice.setCoffeeTable(coffeeTable);
        invoice.setBranchShop(branchShop);
        invoice.setOrderType(orderType);
        invoice.setVAT(invoiceRequestDto.getVAT());
        invoice.setTotalPrice(invoiceRequestDto.getTotalPrice());
        invoice.setTotalDiscount(invoiceRequestDto.getTotalDiscount());
        invoice.setDate(ZonedDateTime.parse(invoiceRequestDto.getDate().withZoneSameInstant(zoneId).toString()));
        invoice.setNumberPosition(invoiceRequestDto.getNumberPosition());
        invoice.setPaymentStatus(invoiceRequestDto.isPaymentStatus());
        invoiceRepository.save(invoice);
        return new ResponseDto(HttpStatus.OK.value(), "Edit invoice successful", null);
    }
    public ResponseDto deleteInvoice(Integer id){
        Invoice invoice = invoiceRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        invoice.setEnable(false);
        invoiceRepository.save(invoice);
        return new ResponseDto(HttpStatus.OK.value(), "Delete invoice successful", null);
    }
}
