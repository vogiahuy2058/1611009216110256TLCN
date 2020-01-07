package com.springboot.angular.coffeesystem.service.invoice;

import com.springboot.angular.coffeesystem.dto.*;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;
    ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
        //get current username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByAccountUsername(username)
                .orElseThrow(()-> new NotFoundException("Username not found"));
        BranchShop branchShop = branchShopRepository.findByEmployees(employee)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        OrderType orderType = orderTypeRepository.findByNameAndEnable(invoiceRequestDto.getOrderType(), true)
                .orElseThrow(()-> new NotFoundException("Order type not found"));
        invoice.setCustomer(customer);
//        invoice.setCoffeeTable(coffeeTable);
        invoice.setBranchShop(branchShop);
        invoice.setOrderType(orderType);
        ZonedDateTime now = ZonedDateTime.now();
        invoice.setDate(now.withZoneSameInstant(zoneId));
        invoiceRepository.save(invoice);
        return new ResponseDto(HttpStatus.OK.value(), "Create invoice successful", null);
    }
    @Transactional
    public ResponseDto getAllInvoiceStatusTrue(){
        List<Invoice> invoices = invoiceRepository.findAllByEnableAndPaymentStatus(true, true);
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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceDateToDate(String fromDate, String toDate, Integer branchShopId){

        LocalDate newFromDate = LocalDate.parse(fromDate, dtf);
        LocalDate newToDate = LocalDate.parse(toDate, dtf);
        List<Invoice> invoices =
                invoiceRepository.findAllByEnableAndPaymentStatusAndBranchShopId(true, true, branchShopId);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
//        try{
//            Date newFromDate = formatter.parse(fromDate);
//            Date newToDate = formatter.parse(toDate);

        invoices.forEach(invoice -> {
            LocalDate dateInvoice = LocalDate.from(invoice.getDate().toLocalDate());
            if(dateInvoice.isBefore(newToDate) && dateInvoice.isAfter(newFromDate)){
                InvoiceResponseDto invoiceResponseDto =
                        mapperObject.InvoiceEntityToDto(invoice);

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
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                        .orElseThrow(()-> new NotFoundException("Username not found"));
                invoiceResponseDto.setCashierName(employee.getName());
                invoiceResponseDtos.add(invoiceResponseDto);
            }

        });
//        }catch (ParseException e){
//            e.printStackTrace();
//        }

        return new ResponseDto(HttpStatus.OK.value(), "All invoice from date to date", invoiceResponseDtos);
    }
    @Transactional
    public PagingResponseDto getAllInvoiceDateToDatePaging(int page, int size, String sort, String sortColumn,
                                                    String fromDate, String toDate, Integer branchShopId){
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        LocalDate newFromDate = LocalDate.parse(fromDate, dtf);
        LocalDate newToDate = LocalDate.parse(toDate, dtf);
        Page<Invoice> invoicePage =
                invoiceRepository.findAllByEnableAndPaymentStatusAndBranchShopId(true, true, branchShopId, pageable);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
//        try{
//            Date newFromDate = formatter.parse(fromDate);
//            Date newToDate = formatter.parse(toDate);

        invoicePage.forEach(invoice -> {
            LocalDate dateInvoice = LocalDate.from(invoice.getDate().toLocalDate());
            if(dateInvoice.isBefore(newToDate) && dateInvoice.isAfter(newFromDate)){
                InvoiceResponseDto invoiceResponseDto =
                        mapperObject.InvoiceEntityToDto(invoice);

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
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                        .orElseThrow(()-> new NotFoundException("Username not found"));
                invoiceResponseDto.setCashierName(employee.getName());
                invoiceResponseDtos.add(invoiceResponseDto);
            }

        });
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements() );
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getAllInvoiceStatusFalse(){
        List<Invoice> invoices = invoiceRepository.findAllByEnableAndPaymentStatus(true, false);
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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        Page<Invoice> invoicePage = invoiceRepository.findAllByEnableAndPaymentStatus(true,true, pageable);
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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);});
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements() );
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    @Override
    public PagingResponseDto getAllInvoicePagingStatusTrue(int page, int size, String sort, String sortColumn) {
                Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        Page<Invoice> invoicePage = invoiceRepository.findAllByEnableAndPaymentStatus(true, true, pageable);
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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);});
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements() );
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }

//    @Override
//    @Transactional
//    public PagingResponseDto getAllInvoicePagingStatusTrue(int page, int size, String sort, String sortColumn) {
//        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
//        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
//        Page<Invoice> invoicePage = invoiceRepository.findAllByEnableAndPaymentStatusPaging(true, true, pageable);
//        invoicePage.forEach(element->{
//            InvoiceResponseDto invoiceResponseDto = mapperObject.InvoiceEntityToDto(element);
//
//            if(element.getCustomer() == null){
//                invoiceResponseDto.setCustomerName(null);
//                invoiceResponseDto.setCustomerPhone(null);
//            }else {
//                invoiceResponseDto.setCustomerName(element.getCustomer().getName());
//                invoiceResponseDto.setCustomerPhone(element.getCustomer().getPhone());
//            }
////            invoiceResponseDto.setCoffeeTable(element.getCoffeeTable().getName());
//            invoiceResponseDto.setBranchShop(element.getBranchShop().getName());
//            invoiceResponseDto.setOrderType(element.getOrderType().getName());
//            invoiceResponseDto.setDate(element.getDate().
//                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
//                    .orElseThrow(()-> new NotFoundException("Username not found"));
//            invoiceResponseDto.setCashierName(employee.getName());
//            invoiceResponseDtos.add(invoiceResponseDto);});
//        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
//                invoicePage.getTotalElements() );
//        return new PagingResponseDto<>(
//                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
//                invoiceResponseDtoPage.getPageable());
//    }

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
        Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                .orElseThrow(()-> new NotFoundException("Username not found"));
        invoiceResponseDto.setCashierName(employee.getName());
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDto );
    }
    @Transactional
    public ResponseDto getFullInvoiceById(Integer InvoiceId){
        Invoice invoice = invoiceRepository.findByIdAndEnable(InvoiceId, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        InvoiceAndInvoiceDetailDto invoiceAndInvoiceDetailDto = mapperObject.InvoiceEntityToDtoFull(invoice);

        if(invoice.getCustomer() == null){
            invoiceAndInvoiceDetailDto.setCustomerName(null);
            invoiceAndInvoiceDetailDto.setCustomerPhone(null);
        }else {
            invoiceAndInvoiceDetailDto.setCustomerName(invoice.getCustomer().getName());
            invoiceAndInvoiceDetailDto.setCustomerPhone(invoice.getCustomer().getPhone());
        }
//        invoiceResponseDto.setCoffeeTable(invoice.getCoffeeTable().getName());
        invoiceAndInvoiceDetailDto.setBranchShop(invoice.getBranchShop().getName());
        invoiceAndInvoiceDetailDto.setOrderType(invoice.getOrderType().getName());
//        invoiceAndInvoiceDetailDto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm:ss Z").format(invoice.getDate()));
        invoiceAndInvoiceDetailDto.setDate(invoice.getDate().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                .orElseThrow(()-> new NotFoundException("Username not found"));
        invoiceAndInvoiceDetailDto.setCashierName(employee.getName());

        //list invoice detail of invoice
        List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findByInvoice(invoice);
        List<InvoiceDetailResponseDto> invoiceDetailResponseDtos = new ArrayList<>();
        Integer serial = 0;
        for (InvoiceDetail element : invoiceDetailList) {
            InvoiceDetailResponseDto invoiceDetailResponseDto = mapperObject.InvoiceDetailEntityToDto(element);
            invoiceDetailResponseDto.setDrinkId(element.getInvoiceDetailId().getDrinkId());
            invoiceDetailResponseDto.setInvoiceId(element.getInvoiceDetailId().getInvoiceId());
            invoiceDetailResponseDto.setDrinkName(element.getDrink().getName());
            invoiceDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            invoiceDetailResponseDtos.add(invoiceDetailResponseDto);
        }
        invoiceAndInvoiceDetailDto.setInvoiceDetailDtoList(invoiceDetailResponseDtos);
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceAndInvoiceDetailDto );
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
//        BranchShop branchShop = branchShopRepository.findByNameAndEnable(invoiceRequestDto.getBranchShop(), true)
//                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        OrderType orderType = orderTypeRepository.findByNameAndEnable(invoiceRequestDto.getOrderType(), true)
                .orElseThrow(()-> new NotFoundException("Order type not found"));
        invoice.setCustomer(customer);
//        invoice.setCoffeeTable(coffeeTable);
//        invoice.setBranchShop(branchShop);
        invoice.setOrderType(orderType);
        invoice.setVat(invoiceRequestDto.getVat());
        invoice.setTotalPrice(invoiceRequestDto.getTotalPrice());
        invoice.setTotalDiscount(invoiceRequestDto.getTotalDiscount());
//        invoice.setDate(ZonedDateTime.parse(invoiceRequestDto.getDate().withZoneSameInstant(zoneId).toString()));
        invoice.setNumberPosition(invoiceRequestDto.getNumberPosition());
        invoice.setPaymentStatus(invoiceRequestDto.isPaymentStatus());
        invoice.setRealPay(invoiceRequestDto.getRealPay());
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

    public ResponseDto deleteInvoiceStatusFalse(){
        List<Invoice> invoiceList = invoiceRepository.findAllByEnableAndPaymentStatus(true, false);
        invoiceList.forEach(element->{
            element.setEnable(false);
            invoiceRepository.save(element);
        });

        return new ResponseDto(HttpStatus.OK.value(), "Delete invoice successful", null);
    }
    public ResponseDto getMaxIdInvoice(){
        Integer idOld = invoiceRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", idOld);
    }
}
