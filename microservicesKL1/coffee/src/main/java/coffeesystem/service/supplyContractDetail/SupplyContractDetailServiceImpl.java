package coffeesystem.service.supplyContractDetail;

import coffeesystem.dto.*;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.SupplyContractDetailId;
import coffeesystem.repository.MaterialRepository;
import coffeesystem.repository.SupplyContractDetailRepository;
import coffeesystem.repository.SupplyContractRepository;
import coffeesystem.repository.UnitRepository;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
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
public class SupplyContractDetailServiceImpl implements SupplyContractDetailService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    SupplyContractDetailRepository repository;
    @Autowired
    SupplyContractRepository supplyContractRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    SupplyContractDetailRepository supplyContractDetailRepository;
    @Autowired
    UnitRepository unitRepository;
    public ResponseDto createSupplyContractDetail(SupplyContractDetailRequestDto detailDto){
        SupplyContractDetail supplyContractDetail =
                this.mapperObject.SupplyContractDetailDtoEntity(detailDto);
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(detailDto.getSupplyContractId(), true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));
        Material material = materialRepository.findByIdAndEnable(detailDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Unit unit = unitRepository.findByNameAndEnable(detailDto.getUnitName(), true)
                .orElseThrow(()-> new NotFoundException("Unit not found"));
        SupplyContractDetailId supplyContractDetailId = new SupplyContractDetailId();
        Integer idOld = supplyContractDetailRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        supplyContractDetailId.setId(idOld+1);
        supplyContractDetailId.setSupplyContractId(supplyContract.getId());
        supplyContractDetailId.setMaterialId(material.getId());
        supplyContractDetail.setSupplyContractDetailId(supplyContractDetailId);
        supplyContractDetail.setSupplyContract(supplyContract);
        supplyContractDetail.setMaterial(material);
        supplyContractDetail.setUnit(unit);
        repository.save(supplyContractDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }
    public ResponseDto editSupplyContractDetail(SupplyContractDetailRequestDto detailDto){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(detailDto.getSupplyContractId(), true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));
        Material material = materialRepository.findByIdAndEnable(detailDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Unit unit = unitRepository.findByNameAndEnable(detailDto.getUnitName(), true)
                .orElseThrow(()-> new NotFoundException("Unit not found"));
        SupplyContractDetail supplyContractDetail =
                repository.findByMaterialAndSupplyContract(material, supplyContract)
                .orElseThrow(()-> new NotFoundException("Supply contract detail not found"));
        supplyContractDetail.setUnitPrice(detailDto.getUnitPrice());
        supplyContractDetail.setAmount(detailDto.getAmount());
        supplyContractDetail.setDeliveryTime(detailDto.getDeliveryTime());
        supplyContractDetail.setPaymentTime(detailDto.getPaymentTime());
        supplyContractDetail.setUnit(unit);
        repository.save(supplyContractDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }

    public ResponseDto editListSupplyContractDetail(List<SupplyContractDetailRequestDto> supplyContractDetailRequestDtoList){
        supplyContractDetailRequestDtoList.forEach(element->{
            editSupplyContractDetail(element);
        });
        return new ResponseDto(HttpStatus.OK.value(), "edit list successful", null);
    }
    public ResponseDto deleteSupplyContractDetail(Integer supplyContractId, Integer materialId, Integer id){
        Material material = materialRepository.findByIdAndEnable(materialId, true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(supplyContractId, true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));

        SupplyContractDetail supplyContractDetail = supplyContractDetailRepository.
                findByMaterialAndSupplyContractAndSupplyContractDetailIdId(material, supplyContract, id)
                .orElseThrow(()-> new NotFoundException("Supply contract detail detail not found"));
        supplyContractDetailRepository.delete(supplyContractDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Delete supply contract detail successful", null);
    }
    @Transactional
    public ResponseDto getSupplyContractDetailBySupplyContractId(Integer supplyContractId){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(supplyContractId, true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));
        List<SupplyContractDetail> supplyContractDetailList = supplyContractDetailRepository.findBySupplyContract(supplyContract);
        List<SupplyContractDetailResponseDto> supplyContractDetailResponseDtos = new ArrayList<>();
        Integer serial = 0;
        for (SupplyContractDetail element : supplyContractDetailList) {
            SupplyContractDetailResponseDto supplyContractDetailResponseDto = mapperObject.SupplyContractEntityToDto(element);
            supplyContractDetailResponseDto.setMaterialId(element.getSupplyContractDetailId().getMaterialId());
            supplyContractDetailResponseDto.setSupplyContractId(element.getSupplyContractDetailId().getSupplyContractId());
            supplyContractDetailResponseDto.setMaterialName(element.getMaterial().getName());
            supplyContractDetailResponseDto.setUnitName(element.getUnit().getName());
            supplyContractDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            supplyContractDetailResponseDtos.add(supplyContractDetailResponseDto);
        }
        return new ResponseDto(HttpStatus.OK.value(), "Successful", supplyContractDetailResponseDtos);
    }
    @Transactional
    public PagingResponseDto getSupplyContractDetailBySupplyContractIdPaging(
            int page, int size, String sort, String sortColumn, Integer supplyContractId) {
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnable(supplyContractId, true)
                .orElseThrow(()-> new NotFoundException("Supply contract not found"));
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<SupplyContractDetailResponseDto> supplyContractDetailResponseDtos = new ArrayList<>();
        Page<SupplyContractDetail> supplyContractDetailPage = supplyContractDetailRepository.
                findAllBySupplyContract(supplyContract, pageable);
        Integer serial = 0;

        for (SupplyContractDetail element : supplyContractDetailPage) {
            SupplyContractDetailResponseDto supplyContractDetailResponseDto = mapperObject.SupplyContractEntityToDto(element);
            supplyContractDetailResponseDto.setMaterialId(element.getSupplyContractDetailId().getMaterialId());
            supplyContractDetailResponseDto.setSupplyContractId(element.getSupplyContractDetailId().getSupplyContractId());
            supplyContractDetailResponseDto.setMaterialName(element.getMaterial().getName());
            supplyContractDetailResponseDto.setUnitName(element.getUnit().getName());
            supplyContractDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            supplyContractDetailResponseDtos.add(supplyContractDetailResponseDto);
        }

        Page<SupplyContractDetailResponseDto> supplyContractDetailResponseDtoPage = new PageImpl<>(supplyContractDetailResponseDtos, pageable,
                supplyContractDetailPage.getTotalElements());
        return new PagingResponseDto<>(
                supplyContractDetailResponseDtoPage.getContent(), supplyContractDetailResponseDtoPage.getTotalElements(),
                supplyContractDetailResponseDtoPage.getTotalPages(), supplyContractDetailResponseDtoPage.getPageable());
    }

    @Transactional
    public ResponseDto getSupplyContractDetailBySupplyContractIdAndStatus(Integer supplyContractId, Integer status){
        SupplyContract supplyContract = supplyContractRepository.findByIdAndEnableAndStatus
                (supplyContractId, true, status)
                .orElseThrow(()-> new NotFoundException("Supply contract has id: " + supplyContractId + " and status: "
                        + status + " not found"));
        List<SupplyContractDetail> supplyContractDetailList =
                supplyContractDetailRepository.findBySupplyContract(supplyContract);
        List<SupplyContractDetailResponseDto> supplyContractDetailResponseDtos = new ArrayList<>();
        Integer serial = 0;
        for (SupplyContractDetail element : supplyContractDetailList) {
            SupplyContractDetailResponseDto supplyContractDetailResponseDto =
                    mapperObject.SupplyContractEntityToDto(element);
            supplyContractDetailResponseDto.setMaterialId(element.getSupplyContractDetailId().getMaterialId());
            supplyContractDetailResponseDto.setSupplyContractId(element.getSupplyContractDetailId().getSupplyContractId());
            supplyContractDetailResponseDto.setMaterialName(element.getMaterial().getName());
            supplyContractDetailResponseDto.setUnitName(element.getUnit().getName());
            supplyContractDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            supplyContractDetailResponseDtos.add(supplyContractDetailResponseDto);
        }
        return new ResponseDto(HttpStatus.OK.value(), "Successful", supplyContractDetailResponseDtos);
    }
    @Transactional
    public ResponseDto getSupplyContractDetailByID(Integer id){
        SupplyContractDetail supplyContractDetail = supplyContractDetailRepository.findBySupplyContractDetailIdId(id)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        SupplyContractDetailResponseDto supplyContractDetailResponseDto =
                mapperObject.SupplyContractEntityToDto(supplyContractDetail);
        supplyContractDetailResponseDto.setMaterialId(supplyContractDetail.getSupplyContractDetailId().getMaterialId());
        supplyContractDetailResponseDto.setSupplyContractId(supplyContractDetail.getSupplyContractDetailId().getSupplyContractId());
        supplyContractDetailResponseDto.setMaterialName(supplyContractDetail.getMaterial().getName());
        supplyContractDetailResponseDto.setSerial(1);
        supplyContractDetailResponseDto.setUnitName(supplyContractDetail.getUnit().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", supplyContractDetailResponseDto );
    }
    public ResponseDto getMaxIdSupplyContractDetail(){
        Integer idOld = supplyContractDetailRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", idOld);
    }
}
