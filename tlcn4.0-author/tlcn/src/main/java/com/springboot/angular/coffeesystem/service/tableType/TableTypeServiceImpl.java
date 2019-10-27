package com.springboot.angular.coffeesystem.service.tableType;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.TableTypeDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.CoffeeTable;
import com.springboot.angular.coffeesystem.model.DrinkType;
import com.springboot.angular.coffeesystem.model.TableType;
import com.springboot.angular.coffeesystem.repository.CoffeeTableRepository;
import com.springboot.angular.coffeesystem.repository.TableTypeRepository;
import com.springboot.angular.coffeesystem.service.coffeeTable.CoffeeTableService;
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
public class TableTypeServiceImpl implements TableTypeService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    TableTypeRepository tableTypeRepository;
    @Autowired
    CoffeeTableRepository coffeeTableRepository;
    @Autowired
    CoffeeTableService coffeeTableService;
    public ResponseDto createTableType(TableTypeDto tableTypeDto){
        TableType tableType = this.mapperObject.TableTypeDtoToEntity(tableTypeDto);
        tableTypeRepository.save(tableType);
        return new ResponseDto(HttpStatus.OK.value(), "create table type successful", null);
    }
    @Transactional
    public ResponseDto getAllTableType(){
        List<TableType> tableTypes = tableTypeRepository.findAllByEnable(true);
        List<TableTypeDto> tableTypeDtos = new ArrayList<>();
        tableTypes.forEach(element->{
            TableTypeDto tableTypeDto = mapperObject.TableTypeEntityToDto(element);
            tableTypeDtos.add(tableTypeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All table type", tableTypeDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllTableTypePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<TableTypeDto> tableTypeDtos = new ArrayList<>();
        Page<TableType> tableTypePage = tableTypeRepository.findAllByEnable(true, pageable);
        tableTypePage.forEach(element->{
            TableTypeDto tableTypeDto = mapperObject.TableTypeEntityToDto(element);
            tableTypeDtos.add(tableTypeDto);});
        Page<TableTypeDto> tableTypeDtoPage = new PageImpl<>(tableTypeDtos, pageable,
                tableTypePage.getTotalElements() );
        return new PagingResponseDto<>(
                tableTypeDtoPage.getContent(), tableTypeDtoPage.getTotalElements(), tableTypeDtoPage.getTotalPages(),
                tableTypeDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getTableTypeById(Integer id){
        TableType tableType = tableTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        TableTypeDto tableTypeDto = mapperObject.TableTypeEntityToDto(tableType);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", tableTypeDto);
    }
    public ResponseDto deleteTableType(Integer id){
        TableType tableType = tableTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete table when table type was deleted
        List<CoffeeTable> coffeeTables = coffeeTableRepository.findByTableTypeId(id);
        coffeeTables.forEach(element->{
            coffeeTableService.deleteCoffeeTable(element.getId());
        });
        tableType.setEnable(false);
        tableTypeRepository.save(tableType);
        return new ResponseDto(HttpStatus.OK.value(), "Delete table type successful", null);
    }
    public ResponseDto editTableType(TableTypeDto tableTypeDto){
        TableType tableType = tableTypeRepository.findById(tableTypeDto.getId())
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        tableType.setName(tableTypeDto.getName());
        tableTypeRepository.save(tableType);
        return new ResponseDto(HttpStatus.OK.value(), "Edit table type successful",
                null);
    }
}
