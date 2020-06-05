package coffeesystem.service.supplyContractDetail;

import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplyContractDetailDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Material;
import coffeesystem.model.SupplyContract;
import coffeesystem.model.SupplyContractDetail;
import coffeesystem.model.embedding.SupplyContractId;
import coffeesystem.repository.MaterialRepository;
import coffeesystem.repository.SupplyContractDetailRepository;
import coffeesystem.repository.SupplyContractRepository;
import coffeesystem.util.MapperObject;
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
