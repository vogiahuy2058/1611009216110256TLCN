package coffeesystem.service.invoice;


import coffeesystem.dto.*;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.repository.*;
import coffeesystem.service.amountMaterialUsed.AmountMaterialUsedService;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
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
import java.time.LocalDateTime;
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
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    AmountMaterialUsedService amountMaterialUsedService;
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
        invoice.setStatus(0);
        ZonedDateTime now = ZonedDateTime.now();
        invoice.setDate(now.withZoneSameInstant(zoneId));
        invoiceRepository.save(invoice);
        return new ResponseDto(HttpStatus.OK.value(), "Create invoice successful", null);
    }
    @Transactional
    public ResponseDto getAllInvoiceByStatus(Integer status){
        List<Invoice> invoices = invoiceRepository.findAllByEnableAndStatus(true, status);
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
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceByStatusAndIdBranchShop(Integer status, Integer idBranchShop){
        List<Invoice> invoices = invoiceRepository.findAllByEnableAndStatusAndBranchShopId(true, status, idBranchShop);
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
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceByFilter(String fromDate, String toDate, Integer branchShopId){
        if(!fromDate.equals("nulldate") && !toDate.equals("nulldate") && branchShopId != 0){
            return getAllInvoiceDateToDateByBranchShopId(fromDate, toDate, branchShopId);
        }
        else if(fromDate.equals("nulldate") && toDate.equals("nulldate") && branchShopId != 0){
            return getAllInvoiceByBranchShopId(branchShopId);
        }

        else if(!fromDate.equals("nulldate") && !fromDate.equals("nulldate") && branchShopId == 0){
            return getAllInvoiceDateToDate(fromDate, toDate);
        }
        else {
            return getAllInvoiceStatus2();
        }
    }
    @Transactional
    public ResponseDto getAllInvoiceDateToDateByBranchShopId(String fromDate, String toDate, Integer branchShopId){
        ZonedDateTime newFromDate = LocalDateTime.parse(fromDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);
        ZonedDateTime newToDate = LocalDateTime.parse(toDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);

        List<Invoice> invoiceList =
                invoiceRepository.findByEnableAndPaymentStatusAndBranchShopIdAndDate(
                        true, 2, branchShopId, newFromDate, newToDate);

        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();

        invoiceList.forEach(invoice -> {
            InvoiceResponseDto invoiceResponseDto = mapperObject.InvoiceEntityToDto(invoice);

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
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);

        });

        return new ResponseDto(HttpStatus.OK.value(),
                "All invoice from date to date and id branch shop", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceByBranchShopId(Integer branchShopId){

        List<Invoice> invoiceList =
                invoiceRepository.findAllByEnableAndStatusAndBranchShopId(
                        true, 2, branchShopId);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();

        invoiceList.forEach(invoice -> {

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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);

        });
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        return new ResponseDto(HttpStatus.OK.value(), "All invoice by id branch shop", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceDateToDate(String fromDate, String toDate){
        ZonedDateTime newFromDate = LocalDateTime.parse(fromDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);
        ZonedDateTime newToDate = LocalDateTime.parse(toDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);

        List<Invoice> invoiceList =
                invoiceRepository.findByEnableAndPaymentStatusAndDate(
                        true, 2, newFromDate, newToDate);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
//        try{
//            Date newFromDate = formatter.parse(fromDate);
//            Date newToDate = formatter.parse(toDate);

        invoiceList.forEach(invoice -> {

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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);

        });
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        return new ResponseDto(HttpStatus.OK.value(), "All invoice date to date", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceStatus2() {
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        List<Invoice> invoiceList = invoiceRepository.findAllByEnableAndStatus(true, 2);
        invoiceList.forEach(element->{
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
            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);});
        return new ResponseDto(HttpStatus.OK.value(), "All invoice not filter", invoiceResponseDtos);
    }
    @Transactional
    public PagingResponseDto getAllInvoiceByFilterPaging(int page, int size, String sort, String sortColumn,
                                                         String fromDate, String toDate, Integer branchShopId){
        if(!fromDate.equals("nulldate") && !toDate.equals("nulldate") && branchShopId != 0){
            return getAllInvoiceDateToDateByBranchShopPaging(page, size, sort, sortColumn, fromDate, toDate, branchShopId);
        }
        else if(fromDate.equals("nulldate") && toDate.equals("nulldate") && branchShopId != 0){
            return getAllInvoiceByBranchShopIdPaging(page, size, sort, sortColumn, branchShopId);
        }

        else if(!fromDate.equals("nulldate") && !fromDate.equals("nulldate") && branchShopId == 0){
            return getAllInvoiceDateToDatePaging(page, size, sort, sortColumn, fromDate, toDate);
        }
        else {
            return getAllInvoiceStatus2Paging(page, size, sort, sortColumn);
        }
    }
    @Transactional
    public PagingResponseDto getAllInvoiceDateToDateByBranchShopPaging(int page, int size, String sort, String sortColumn,
                                                    String fromDate, String toDate, Integer branchShopId){
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
//        LocalDate newFromDate = LocalDate.parse(fromDate, dtf);
//        LocalDate newToDate = LocalDate.parse(toDate, dtf);
        ZonedDateTime newFromDate = LocalDateTime.parse(fromDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);
        ZonedDateTime newToDate = LocalDateTime.parse(toDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);

        Page<Invoice> invoicePage =
                invoiceRepository.findByEnableAndPaymentStatusAndBranchShopIdAndDate(
                        true, 2, branchShopId, newFromDate, newToDate, pageable);

        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();

        invoicePage.forEach(invoice -> {
            InvoiceResponseDto invoiceResponseDto = mapperObject.InvoiceEntityToDto(invoice);

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
                Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                        .orElseThrow(()-> new NotFoundException("Username not found"));
                invoiceResponseDto.setCashierName(employee.getName());
                invoiceResponseDtos.add(invoiceResponseDto);

        });

        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements());
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    public PagingResponseDto getAllInvoiceByBranchShopIdPaging(int page, int size, String sort, String sortColumn,
                                                               Integer branchShopId){
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);

        Page<Invoice> invoicePage =
                invoiceRepository.findAllByEnableAndStatusAndBranchShopId(
                        true, 2, branchShopId, pageable);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();

        invoicePage.forEach(invoice -> {

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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);

        });
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements());

        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(),
                invoiceResponseDtoPage.getTotalPages(), invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    public PagingResponseDto getAllInvoiceDateToDatePaging(int page, int size, String sort, String sortColumn,
                                                           String fromDate, String toDate){
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        ZonedDateTime newFromDate = LocalDateTime.parse(fromDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);
        ZonedDateTime newToDate = LocalDateTime.parse(toDate,
                DateTimeFormatter.ISO_DATE_TIME).atZone(zoneId);

        Page<Invoice> invoicePage =
                invoiceRepository.findByEnableAndPaymentStatusAndDate(
                        true, 2, newFromDate, newToDate, pageable);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
//        try{
//            Date newFromDate = formatter.parse(fromDate);
//            Date newToDate = formatter.parse(toDate);

        invoicePage.forEach(invoice -> {

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
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);

        });
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements());
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    @Override
    public PagingResponseDto getAllInvoiceStatus2Paging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        Page<Invoice> invoicePage = invoiceRepository.findAllByEnableAndStatus(true, 2, pageable);
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
            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);});
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements());
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
    @Transactional
    @Override
    public PagingResponseDto getAllInvoiceStatus2PagingByBranchShop(int page, int size, String sort,
                                                                    String sortColumn, Integer idBranchShop) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        Page<Invoice> invoicePage = invoiceRepository.findAllByEnableAndStatusAndBranchShopId(true,
                2,idBranchShop, pageable);
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
            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);});
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements());
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }
// get invoice not filter
    @Transactional
    @Override
    public PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InvoiceResponseDto> invoiceResponseDtos = new ArrayList<>();
        Page<Invoice> invoicePage = invoiceRepository.findAllByEnableAndStatus(true, 2, pageable);

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
            Employee employee = employeeRepository.findByAccountUsername(element.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);});
        Page<InvoiceResponseDto> invoiceResponseDtoPage = new PageImpl<>(invoiceResponseDtos, pageable,
                invoicePage.getTotalElements());
        return new PagingResponseDto<>(
                invoiceResponseDtoPage.getContent(), invoiceResponseDtoPage.getTotalElements(), invoiceResponseDtoPage.getTotalPages(),
                invoiceResponseDtoPage.getPageable());
    }


    @Transactional
    public ResponseDto getAllInvoiceStatus0(){
        List<Invoice> invoices = invoiceRepository.findAllByEnableAndStatus(true, 0);
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
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInvoiceStatus0ByBranchShop(Integer idBranchShop){
        List<Invoice> invoices = invoiceRepository.findAllByEnableAndStatusAndBranchShopId(true, 0, idBranchShop);
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
            Employee employee = employeeRepository.findByAccountUsername(invoice.getLastModifiedBy())
                    .orElseThrow(()-> new NotFoundException("Username not found"));
            invoiceResponseDto.setCashierName(employee.getName());
            invoiceResponseDtos.add(invoiceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All invoice", invoiceResponseDtos);
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
        ZonedDateTime now = ZonedDateTime.now();
        invoice.setDate(now.withZoneSameInstant(zoneId));
        invoice.setNumberPosition(invoiceRequestDto.getNumberPosition());
        invoice.setStatus(invoiceRequestDto.getStatus());
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

    public ResponseDto deleteInvoiceStatus0(){
        List<Invoice> invoiceList = invoiceRepository.findAllByEnableAndStatus(true, 0);
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
    public ResponseDto updateAmountMaterialUsed(Integer invoiceId){
        Invoice invoice = invoiceRepository.findByIdAndEnable(invoiceId, true).get();
        List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findByInvoice(invoice);
        invoiceDetailList.forEach(invoiceDetail -> {
            List<Recipe> recipeList = recipeRepository.findByDrinkIdAndEnable(
                    invoiceDetail.getInvoiceDetailId().getDrinkId(), true);
            recipeList.forEach(recipe -> {
                amountMaterialUsedService.updateAmountMaterialUsed(
                        invoice.getBranchShop().getId(), recipe.getMaterial().getId(),
                        //luong nguyen lieu min * so ly thuc uong cua chi tiet hoa don
                        recipe.getMinAmount()*invoiceDetail.getAmount(),
                        //luong nguyen lieu max * so ly thuc uong cua chi tiet hoa don
                        recipe.getMaxAmount()* invoiceDetail.getAmount(),
                        recipe.getAverageAmount()* invoiceDetail.getAmount()
                );
            });
        });
        return new ResponseDto(HttpStatus.OK.value(), "Update amount material used" +
                " successful", null);
    }

//    public ResponseDto salesStatistics(String fromDate, String toDate){
//        //tính tong doanh thu cua chi nhanh
//        List<BranchShop> branchShopList = branchShopRepository.findAllByEnable(true);
//        branchShopList.forEach();
//
//    }

}
