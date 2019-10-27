package com.springboot.angular.coffeesystem.service.supplyContract;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.Material;
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
    public ResponseDto createSupplyContract(SupplyContractDto supplyContractDto){
        SupplyContract supplyContract = this.mapperObject.SupplyContractDtoToEntity(supplyContractDto);
        Supplier supplier = supplierRepository.findAllByNameAndEnable(supplyContractDto.getSupplier(), true)
                .orElseThrow(()-> new NotFoundException("Supply not found"));
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(supplyContractDto.getBranchShop(),
                true).orElseThrow(()-> new NotFoundException("Branch shop not found"));
        supplyContract.setBranchShop(branchShop);
        supplyContract.setSupplier(supplier);
        supplyContractRepository.save(supplyContract);
        return new ResponseDto(HttpStatus.OK.value(), "Create supply contract successful", null);
    }
    @Transactional
    public ResponseDto getAllSupplyContract(){
        List<SupplyContract> supplyContracts = supplyContractRepository.findAllByEnable(true);
        List<SupplyContractDto> supplyContractDtos = new ArrayList<>();
        supplyContracts.forEach(element->{
            SupplyContractDto supplyContractDto = mapperObject.SupplyContractEntityToDto(element);
            supplyContractDto.setBranchShop(element.getBranchShop().getName());
            supplyContractDto.setSupplier(element.getSupplier().getAddress());
            supplyContractDtos.add(supplyContractDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All supply contract", supplyContractDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllSupplyContractPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<SupplyContractDto> supplyContractDtos = new ArrayList<>();
        Page<SupplyContract> supplyContractPage = supplyContractRepository.findAllByEnable(true, pageable);
        supplyContractPage.forEach(element->{
            SupplyContractDto supplyContractDto = mapperObject.SupplyContractEntityToDto(element);
            supplyContractDto.setBranchShop(element.getBranchShop().getName());
            supplyContractDto.setSupplier(element.getSupplier().getName());
            supplyContractDtos.add(supplyContractDto);});
        Page<SupplyContractDto> supplyContractDtoPage = new PageImpl<>(supplyContractDtos, pageable,
                supplyContractPage.getTotalElements() );
        return new PagingResponseDto<>(
                supplyContractDtoPage.getContent(), supplyContractDtoPage.getTotalElements(), supplyContractDtoPage.getTotalPages(),
                supplyContractDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getSupplyContractById(Integer id){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        SupplyContractDto supplyContractDto = mapperObject.SupplyContractEntityToDto(supplyContract);
        supplyContractDto.setSupplier(supplyContract.getSupplier().getName());
        supplyContractDto.setBranchShop(supplyContract.getBranchShop().getName());
        return new ResponseDto(HttpStatus.OK.value(), "All supply contract", supplyContractDto );
    }
    public ResponseDto deleteSupplyContract(Integer id){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        supplyContract.setEnable(false);
        supplyContractRepository.save(supplyContract);
        return new ResponseDto(HttpStatus.OK.value(), "Delete supply successful", null);
    }
    public ResponseDto editSupplyContract(SupplyContractDto supplyContractDto){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(supplyContractDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        Supplier supplier = supplierRepository.findAllByNameAndEnable(supplyContractDto.getSupplier(), true)
                .orElseThrow(()-> new NotFoundException("Supply not found"));
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(supplyContractDto.getBranchShop(),
                true).orElseThrow(()-> new NotFoundException("Branch shop not found"));
        supplyContract.setBranchShop(branchShop);
        supplyContract.setSupplier(supplier);
        supplyContract.setTotalPrice(supplyContractDto.getTotalPrice());
        supplyContract.setDate(supplyContractDto.getDate());
        supplyContractRepository.save(supplyContract);
        return new ResponseDto(HttpStatus.OK.value(), "Edit supply contract successful", null);
    }
}
