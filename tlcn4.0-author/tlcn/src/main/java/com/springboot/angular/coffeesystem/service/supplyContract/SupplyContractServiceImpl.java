package com.springboot.angular.coffeesystem.service.supplyContract;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractRequestDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.Supplier;
import com.springboot.angular.coffeesystem.model.SupplyContract;
import com.springboot.angular.coffeesystem.repository.BranchShopRepository;
import com.springboot.angular.coffeesystem.repository.SupplierRepository;
import com.springboot.angular.coffeesystem.repository.SupplyContractRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplyContractServiceImpl implements SupplyContractService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    SupplyContractRepository supplyContractRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ResponseDto createSupplyContract(SupplyContractRequestDto supplyContractRequestDto){
        SupplyContract supplyContract = this.mapperObject.SupplyContractDtoToEntity(supplyContractRequestDto);
        Supplier supplier = supplierRepository.findAllByNameAndEnable(supplyContractRequestDto.getSupplier(), true)
                .orElseThrow(()-> new NotFoundException("Supply not found"));
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(supplyContractRequestDto.getBranchShop(),
                true).orElseThrow(()-> new NotFoundException("Branch shop not found"));
        supplyContract.setBranchShop(branchShop);
        supplyContract.setSupplier(supplier);
        supplyContractRepository.save(supplyContract);
        return new ResponseDto(HttpStatus.OK.value(), "Create supply contract successful", null);
    }
    @Transactional
    public ResponseDto getAllSupplyContract(){
        List<SupplyContract> supplyContracts = supplyContractRepository.findAllByEnable(true);
        List<SupplyContractResponseDto> supplyContractResponseDtos = new ArrayList<>();
        supplyContracts.forEach(element->{
            SupplyContractResponseDto supplyContractResponseDto =
                    mapperObject.SupplyContractEntityToDto1(element);
            supplyContractResponseDto.setDate(element.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            supplyContractResponseDto.setBranchShop(element.getBranchShop().getName());
            supplyContractResponseDto.setSupplier(element.getSupplier().getName());
            supplyContractResponseDtos.add(supplyContractResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All supply contract", supplyContractResponseDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllSupplyContractPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<SupplyContractResponseDto> supplyContractResponseDtos = new ArrayList<>();
        Page<SupplyContract> supplyContractPage = supplyContractRepository.findAllByEnable(true, pageable);
        supplyContractPage.forEach(element->{
            SupplyContractResponseDto supplyContractResponseDto =
                    mapperObject.SupplyContractEntityToDto1(element);
            supplyContractResponseDto.setDate(element.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            supplyContractResponseDto.setBranchShop(element.getBranchShop().getName());
            supplyContractResponseDto.setSupplier(element.getSupplier().getName());
            supplyContractResponseDtos.add(supplyContractResponseDto);});
        Page<SupplyContractResponseDto> supplyContractDtoPage = new PageImpl<>(supplyContractResponseDtos, pageable,
                supplyContractPage.getTotalElements() );
        return new PagingResponseDto<>(
                supplyContractDtoPage.getContent(), supplyContractDtoPage.getTotalElements(), supplyContractDtoPage.getTotalPages(),
                supplyContractDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getSupplyContractById(Integer id){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        SupplyContractResponseDto supplyContractResponseDto =
                mapperObject.SupplyContractEntityToDto1(supplyContract);
        supplyContractResponseDto.setDate(supplyContract.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        supplyContractResponseDto.setSupplier(supplyContract.getSupplier().getName());
        supplyContractResponseDto.setBranchShop(supplyContract.getBranchShop().getName());
        return new ResponseDto(HttpStatus.OK.value(), "All supply contract", supplyContractResponseDto);
    }
    public ResponseDto deleteSupplyContract(Integer id){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        supplyContract.setEnable(false);
        supplyContractRepository.save(supplyContract);
        return new ResponseDto(HttpStatus.OK.value(), "Delete supply successful", null);
    }
    public ResponseDto editSupplyContract(SupplyContractRequestDto supplyContractRequestDto){

        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(supplyContractRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        Supplier supplier = supplierRepository.findAllByNameAndEnable(supplyContractRequestDto.getSupplier(), true)
                .orElseThrow(()-> new NotFoundException("Supply not found"));
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(supplyContractRequestDto.getBranchShop(),
                true).orElseThrow(()-> new NotFoundException("Branch shop not found"));
        supplyContract.setBranchShop(branchShop);
        supplyContract.setSupplier(supplier);
        supplyContract.setTotalPrice(supplyContractRequestDto.getTotalPrice());
        supplyContract.setDate(supplyContractRequestDto.getDate());
        supplyContractRepository.save(supplyContract);
        return new ResponseDto(HttpStatus.OK.value(), "Edit supply contract successful", null);
    }
}
