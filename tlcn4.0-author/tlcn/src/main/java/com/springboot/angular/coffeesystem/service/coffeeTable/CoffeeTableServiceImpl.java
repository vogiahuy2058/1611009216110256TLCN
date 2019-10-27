package com.springboot.angular.coffeesystem.service.coffeeTable;

import com.springboot.angular.coffeesystem.dto.CoffeeTableDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.CoffeeTable;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.TableType;
import com.springboot.angular.coffeesystem.repository.CoffeeTableRepository;
import com.springboot.angular.coffeesystem.repository.InvoiceRepository;
import com.springboot.angular.coffeesystem.repository.TableTypeRepository;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeTableServiceImpl implements CoffeeTableService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    CoffeeTableRepository coffeeTableRepository;
    @Autowired
    TableTypeRepository tableTypeRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceService invoiceService;
    public ResponseDto createCoffeeTable(CoffeeTableDto coffeeTableDto){
        CoffeeTable coffeeTable = this.mapperObject.CoffeeTableDtoToEntity(coffeeTableDto);
        TableType tableType = tableTypeRepository.findByNameAndEnable(coffeeTableDto.getTableType(), true)
                .orElseThrow(()-> new NotFoundException("Table not found"));
        coffeeTable.setTableType(tableType);
        coffeeTableRepository.save(coffeeTable);
        return new ResponseDto(HttpStatus.OK.value(), "Create table successful", null);
    }
    @Transactional
    public ResponseDto getAllCoffeeTable(){
        List<CoffeeTable> coffeeTables = coffeeTableRepository.findAllByEnable(true);
        List<CoffeeTableDto> coffeeTableDtos = new ArrayList<>();
        coffeeTables.forEach(element->{
            CoffeeTableDto coffeeTableDto = mapperObject.CoffeeTableEntityToDto(element);
            coffeeTableDto.setTableType(element.getTableType().getName());
            coffeeTableDtos.add(coffeeTableDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All table", coffeeTableDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllCoffeeTablePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<CoffeeTableDto> coffeeTableDtos = new ArrayList<>();
        Page<CoffeeTable> branchShopPage = coffeeTableRepository.findAllByEnable(true, pageable);
        branchShopPage.forEach(element->{
            CoffeeTableDto coffeeTableDto = mapperObject.CoffeeTableEntityToDto(element);
            coffeeTableDto.setTableType(element.getTableType().getName());
            coffeeTableDtos.add(coffeeTableDto);
        });
        Page<CoffeeTableDto> coffeeTableDtoPage = new PageImpl<>(coffeeTableDtos, pageable, branchShopPage.getTotalElements() );
        return new PagingResponseDto<>(
                coffeeTableDtoPage.getContent(), coffeeTableDtoPage.getTotalElements(), coffeeTableDtoPage.getTotalPages(),
                coffeeTableDtoPage.getPageable());
    }

    @Transactional
    public ResponseDto getCoffeeTableById(Integer id){
        CoffeeTable coffeeTable = coffeeTableRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        CoffeeTableDto coffeeTableDto = mapperObject.CoffeeTableEntityToDto(coffeeTable);
        coffeeTableDto.setTableType(coffeeTable.getTableType().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", coffeeTableDto);
    }
    public ResponseDto deleteCoffeeTable(Integer id){
        CoffeeTable coffeeTable = coffeeTableRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete invoice when coffee table was deleted
        List<Invoice> invoices = invoiceRepository.findByCoffeeTableId(id);
        invoices.forEach(element->{
            invoiceService.deleteInvoice(element.getId());
        });
        coffeeTable.setEnable(false);
        coffeeTableRepository.save(coffeeTable);
        return new ResponseDto(HttpStatus.OK.value(), "Delete table successful", null);
    }
    public ResponseDto editCoffeeTable(CoffeeTableDto coffeeTableDto){
        CoffeeTable coffeeTable = coffeeTableRepository.findByIdAndEnable(coffeeTableDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        TableType tableType = tableTypeRepository.findByNameAndEnable(coffeeTableDto.getTableType(), true)
                .orElseThrow(()-> new NotFoundException("Table not found"));
        coffeeTable.setName(coffeeTableDto.getName());
        coffeeTable.setNumberOfChair(coffeeTableDto.getNumberOfChair());
        coffeeTable.setNote(coffeeTableDto.getNote());
        coffeeTable.setTableType(tableType);
        coffeeTableRepository.save(coffeeTable);
        return new ResponseDto(HttpStatus.OK.value(), "Edit table successful", null);
    }
}
