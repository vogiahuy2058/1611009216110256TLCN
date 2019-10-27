package com.springboot.angular.coffeesystem.service.supplyContractDetail;

import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.SupplyContractDetailDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.BranchShop;
import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.SupplyContract;
import com.springboot.angular.coffeesystem.model.SupplyContractDetail;
import com.springboot.angular.coffeesystem.model.embedding.SupplyContractId;
import com.springboot.angular.coffeesystem.repository.BranchShopRepository;
import com.springboot.angular.coffeesystem.repository.MaterialRepository;
import com.springboot.angular.coffeesystem.repository.SupplyContractDetailRepository;
import com.springboot.angular.coffeesystem.repository.SupplyContractRepository;
import com.springboot.angular.coffeesystem.service.supplyContract.SupplyContractService;
import com.springboot.angular.coffeesystem.util.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SupplyContractDetailServiceImpl implements SupplyContractDetailService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    SupplyContractDetailRepository repository;
    @Autowired
    SupplyContractRepository supplyContractRepository;
    @Autowired
    MaterialRepository materialRepository;
    public ResponseDto createSupplyContractDetail(SupplyContractDetailDto detailDto){
        SupplyContractDetail supplyContractDetail =
                this.mapperObject.SupplyContractDetailDtoEntity(detailDto);
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(detailDto.getSupplyContractId(), true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));
        Material material = materialRepository.findByIdAndEnable(detailDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        SupplyContractId supplyContractId = new SupplyContractId();
        supplyContractId.setSupplyContractId(supplyContract.getId());
        supplyContractId.setMaterialId(material.getId());
        supplyContractDetail.setId(supplyContractId);
        supplyContractDetail.setSupplyContract(supplyContract);
        supplyContractDetail.setMaterial(material);
        repository.save(supplyContractDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }
    public ResponseDto editSupplyContractDetail(SupplyContractDetailDto detailDto){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(detailDto.getSupplyContractId(), true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));
        Material material = materialRepository.findByIdAndEnable(detailDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        SupplyContractDetail supplyContractDetail =
                repository.findByMaterialAndSupplyContract(material, supplyContract)
                .orElseThrow(()-> new NotFoundException("Supply contract detail not found"));
        supplyContractDetail.setUnitPrice(detailDto.getUnitPrice());
        supplyContractDetail.setAmount(detailDto.getAmount());
        supplyContractDetail.setDeliveryTime(detailDto.getDeliveryTime());
        supplyContractDetail.setPaymentTime(detailDto.getPaymentTime());
        repository.save(supplyContractDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }
}
