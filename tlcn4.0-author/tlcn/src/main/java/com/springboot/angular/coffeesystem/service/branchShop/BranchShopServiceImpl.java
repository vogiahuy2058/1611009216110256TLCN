package com.springboot.angular.coffeesystem.service.branchShop;

import com.springboot.angular.coffeesystem.dto.BranchShopDto;
import com.springboot.angular.coffeesystem.dto.EmployeeResponseDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.Employee;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.SupplyContract;
import com.springboot.angular.coffeesystem.repository.BranchShopRepository;
import com.springboot.angular.coffeesystem.repository.EmployeeRepository;
import com.springboot.angular.coffeesystem.repository.InvoiceRepository;
import com.springboot.angular.coffeesystem.repository.SupplyContractRepository;
import com.springboot.angular.coffeesystem.service.employee.EmployeeService;
import com.springboot.angular.coffeesystem.service.invoice.InvoiceService;
import com.springboot.angular.coffeesystem.service.supplyContract.SupplyContractService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BranchShopServiceImpl implements BranchShopService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    BranchShopRepository branchShopRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    SupplyContractRepository supplyContractRepository;
    @Autowired
    SupplyContractService supplyContractService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    public ResponseDto createBranchShop(BranchShopDto branchShopDto){
        BranchShop branchShop = this.mapperObject.BranchShopDtoToEntity(branchShopDto);
        branchShopRepository.save(branchShop);
        return new ResponseDto(HttpStatus.OK.value(), "Create branch shop successful", null);
    }
    @Transactional
    public ResponseDto getAllBranchShop(){
        List<BranchShop> branchShops = branchShopRepository.findAllByEnable(true);
        List<BranchShopDto> branchShopDtos = new ArrayList<>();
        branchShops.forEach(element->{
            BranchShopDto branchShopDto = mapperObject.BranchShopEntityToDto(element);
            branchShopDtos.add(branchShopDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All branch shop",branchShopDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllBranchShopPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<BranchShopDto> branchShopDtos = new ArrayList<>();
        Page<BranchShop> branchShopPage = branchShopRepository.findAllByEnable(true, pageable);
        branchShopPage.forEach(element->{
            BranchShopDto branchShopDto = mapperObject.BranchShopEntityToDto(element);
            branchShopDtos.add(branchShopDto);});
        Page<BranchShopDto> branchShopDtoPage = new PageImpl<>(branchShopDtos, pageable, branchShopPage.getTotalElements());
        return new PagingResponseDto<>(
                branchShopDtoPage.getContent(), branchShopDtoPage.getTotalElements(), branchShopDtoPage.getTotalPages(),
                branchShopDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getBranchShopById(Integer id){
        BranchShop branchShop = branchShopRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        BranchShopDto branchShopDto = mapperObject.BranchShopEntityToDto(branchShop);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", branchShopDto);
    }
    public ResponseDto deleteBranchShop(Integer id){
        BranchShop branchShop = branchShopRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete invoice when branch shop was deleted
        List<Invoice> invoices = invoiceRepository.findByBranchShopId(id);
        invoices.forEach(element->{
            invoiceService.deleteInvoice(element.getId());
        });
        //delete supply contract when branch shop was deleted
        List<SupplyContract> supplyContracts = supplyContractRepository.findByBranchShopId(id);
        supplyContracts.forEach(element->{
            supplyContractService.deleteSupplyContract(element.getId());
        });
        //delete employee when branch shop was deleted
        List<Employee> employees = employeeRepository.findByBranchShopId(id);
        employees.forEach(element->{
           employeeService.deleteEmployee(element.getId());
        });
        branchShop.setEnable(false);
        branchShopRepository.save(branchShop);
        return new ResponseDto(HttpStatus.OK.value(), "Delete branch shop successful", null);
    }
    public ResponseDto editBranchShop(BranchShopDto branchShopDto){
        BranchShop branchShop = branchShopRepository.findByIdAndEnable(branchShopDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        branchShop.setName(branchShopDto.getName());
        branchShop.setAddress(branchShopDto.getAddress());
        branchShopRepository.save(branchShop);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink type successful", null);
    }
}
