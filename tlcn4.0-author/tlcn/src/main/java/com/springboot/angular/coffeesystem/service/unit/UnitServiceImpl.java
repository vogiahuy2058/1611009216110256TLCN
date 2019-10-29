package com.springboot.angular.coffeesystem.service.unit;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.UnitDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.CoffeeTable;
import com.springboot.angular.coffeesystem.model.Unit;
import com.springboot.angular.coffeesystem.repository.UnitRepository;
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
public class UnitServiceImpl implements UnitService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    UnitRepository unitRepository;
    public ResponseDto createUnit(UnitDto unitDto){
        Unit unit = this.mapperObject.UnitDtoToEntity(unitDto);
        unitRepository.save(unit);
        return new ResponseDto(HttpStatus.OK.value(), "create unit successful", null);
    }
    @Transactional
    public ResponseDto getAllUnit(){
        List<Unit> units = unitRepository.findAllByEnable(true);
        List<UnitDto> unitDtos = new ArrayList<>();
        units.forEach(element->{
            UnitDto unitDto = mapperObject.UnitEntityToDto(element);
            unitDtos.add(unitDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All unit", unitDtos);
    }
    @Transactional
    public ResponseDto getUnitById(Integer id){
        Unit unit = unitRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        UnitDto unitDto = mapperObject.UnitEntityToDto(unit);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", unitDto);
    }
    @Transactional
    public PagingResponseDto getAllUnitPaging(int page, int size, String sort, String sortColumn){
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<UnitDto> unitDtos = new ArrayList<>();
        Page<Unit> unitPage = unitRepository.findAllByEnable(true, pageable);
        unitPage.forEach(element->{
            UnitDto unitDto = mapperObject.UnitEntityToDto(element);
            unitDtos.add(unitDto);});
        Page<UnitDto> unitDtoPage = new PageImpl<>(unitDtos, pageable,
                unitPage.getTotalElements() );
        return new PagingResponseDto<>(
                unitDtoPage.getContent(), unitDtoPage.getTotalElements(), unitDtoPage.getTotalPages(),
                unitDtoPage.getPageable());
    }
    public ResponseDto deleteUnit(Integer id){
        Unit unit = unitRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        unit.setEnable(false);
        unitRepository.save(unit);
        return new ResponseDto(HttpStatus.OK.value(), "Delete unit successful", null);
    }
    public ResponseDto editUnit(UnitDto unitDto){
        Unit unit = unitRepository.findById(unitDto.getId())
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        unit.setName(unitDto.getName());
        unitRepository.save(unit);
        return new ResponseDto(HttpStatus.OK.value(), "Edit unit successful",null);
    }

}
