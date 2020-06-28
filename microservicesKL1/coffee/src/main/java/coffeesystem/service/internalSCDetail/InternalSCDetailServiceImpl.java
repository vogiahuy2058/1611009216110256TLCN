package coffeesystem.service.internalSCDetail;

import coffeesystem.dto.*;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.InternalSCDetailId;
import coffeesystem.model.embedding.SupplyContractDetailId;
import coffeesystem.repository.*;
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
public class InternalSCDetailServiceImpl implements InternalSCDetailService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    InternalSCRepository internalSCRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    InternalSCDetailRepository internalSCDetailRepository;
    @Autowired
    UnitRepository unitRepository;
    public ResponseDto createInternalSCDetail(InternalSCDetailRequestDto internalSCDetailRequestDto){
        InternalSCDetail internalSCDetail =
                this.mapperObject.InternalSCDetailDtoEntity(internalSCDetailRequestDto);
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(
                internalSCDetailRequestDto.getInternalSCId(), true)
                .orElseThrow(()-> new NotFoundException("Internal supply contract not found"));
        Material material = materialRepository.findByIdAndEnable(internalSCDetailRequestDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Unit unit = unitRepository.findByNameAndEnable(internalSCDetailRequestDto.getUnitName(), true)
                .orElseThrow(()-> new NotFoundException("Unit not found"));
        InternalSCDetailId internalSCDetailId = new InternalSCDetailId();
        Integer idOld = internalSCDetailRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        internalSCDetailId.setId(idOld+1);
        internalSCDetailId.setInternalSCId(internalSC.getId());
        internalSCDetailId.setMaterialId(material.getId());
        internalSCDetail.setInternalSCDetailId(internalSCDetailId);
        internalSCDetail.setInternalSC(internalSC);
        internalSCDetail.setMaterial(material);
        internalSCDetail.setUnit(unit);
        internalSCDetailRepository.save(internalSCDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }
    public ResponseDto editInternalSCDetail(InternalSCDetailRequestDto internalSCDetailRequestDto){
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(
                internalSCDetailRequestDto.getInternalSCId(), true)
                .orElseThrow(()-> new NotFoundException("Internal supply contract not found"));
        Material material = materialRepository.findByIdAndEnable(internalSCDetailRequestDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Unit unit = unitRepository.findByNameAndEnable(internalSCDetailRequestDto.getUnitName(), true)
                .orElseThrow(()-> new NotFoundException("Unit not found"));
        InternalSCDetail internalSCDetail =
                internalSCDetailRepository.findByMaterialAndInternalSCAndEnable(material, internalSC, true)
                        .orElseThrow(()-> new NotFoundException("Internal supply contract detail not found"));
//        supplyContractDetail.setUnitPrice(detailDto.getUnitPrice());
        internalSCDetail.setNumberOfRequest(internalSCDetailRequestDto.getNumberOfRequest());
        internalSCDetail.setQuantityAllowed(internalSCDetailRequestDto.getQuantityAllowed());
        internalSCDetail.setQuantityReceived(internalSCDetailRequestDto.getQuantityReceived());
//        supplyContractDetail.setDeliveryTime(detailDto.getDeliveryTime());
//        supplyContractDetail.setPaymentTime(detailDto.getPaymentTime());
        internalSCDetail.setUnit(unit);
        internalSCDetailRepository.save(internalSCDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Edit successful", null);
    }
    public ResponseDto editListInternalSCDetail(List<InternalSCDetailRequestDto> internalSCDetailRequestDtoList){
        internalSCDetailRequestDtoList.forEach(element->{
            editInternalSCDetail(element);
        });
        return new ResponseDto(HttpStatus.OK.value(), "edit list successful", null);
    }

    @Override
    public ResponseDto deleteInternalSCDetail(Integer internalSCId, Integer materialId, Integer id) {
        Material material = materialRepository.findByIdAndEnable(materialId, true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(internalSCId, true)
                .orElseThrow(()-> new NotFoundException("Internal supply contract not found"));

        InternalSCDetail internalSCDetail = internalSCDetailRepository.
                findByMaterialAndInternalSCAndInternalSCDetailIdIdAndEnable(material, internalSC, id, true)
                .orElseThrow(()-> new NotFoundException("Internal supply contract detail detail not found"));
        internalSCDetailRepository.delete(internalSCDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Delete internal supply contract detail successful", null);
    }

    @Override
    @Transactional
    public ResponseDto getInternalSCDetailByInternalSCId(Integer internalSCId) {
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(internalSCId, true)
                .orElseThrow(()-> new NotFoundException("Internal supply contract not found"));
        List<InternalSCDetail> internalSCDetailList = internalSCDetailRepository.findByInternalSCAndEnable(internalSC, true);
        List<InternalSCDetailResponseDto> internalSCDetailResponseDtos = new ArrayList<>();
        Integer serial = 0;
        for (InternalSCDetail element : internalSCDetailList) {
            InternalSCDetailResponseDto internalSCDetailResponseDto = mapperObject.InternalSCDetailEntityToDto(element);
            internalSCDetailResponseDto.setMaterialId(element.getInternalSCDetailId().getMaterialId());
            internalSCDetailResponseDto.setInternalSCId(element.getInternalSCDetailId().getInternalSCId());
            internalSCDetailResponseDto.setMaterialName(element.getMaterial().getName());
//            supplyContractDetailResponseDto.setUnitName(element.getUnit().getName());
            internalSCDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            internalSCDetailResponseDtos.add(internalSCDetailResponseDto);
        }
        return new ResponseDto(HttpStatus.OK.value(), "Successful", internalSCDetailResponseDtos);
    }

    @Override
    @Transactional
    public PagingResponseDto getInternalSCDetailByInternalSCPaging(int page, int size, String sort, String sortColumn, Integer internalSCId) {
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(internalSCId, true)
                .orElseThrow(()-> new NotFoundException("Internal supply contract not found"));
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InternalSCDetailResponseDto> internalSCDetailResponseDtos = new ArrayList<>();
        Page<InternalSCDetail> internalSCDetailPage = internalSCDetailRepository.
                findAllByInternalSCAndEnable(internalSC,true, pageable);
        Integer serial = 0;

        for (InternalSCDetail element : internalSCDetailPage) {
            InternalSCDetailResponseDto internalSCDetailResponseDto = mapperObject.InternalSCDetailEntityToDto(element);
            internalSCDetailResponseDto.setMaterialId(element.getInternalSCDetailId().getMaterialId());
            internalSCDetailResponseDto.setInternalSCId(element.getInternalSCDetailId().getInternalSCId());
            internalSCDetailResponseDto.setMaterialName(element.getMaterial().getName());
//            supplyContractDetailResponseDto.setUnitName(element.getUnit().getName());
            internalSCDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            internalSCDetailResponseDtos.add(internalSCDetailResponseDto);
        }

        Page<InternalSCDetailResponseDto> internalSCDetailResponseDtoPage =
                new PageImpl<>(internalSCDetailResponseDtos, pageable,
                internalSCDetailPage.getTotalElements());
        return new PagingResponseDto<>(
                internalSCDetailResponseDtoPage.getContent(), internalSCDetailResponseDtoPage.getTotalElements(),
                internalSCDetailResponseDtoPage.getTotalPages(), internalSCDetailResponseDtoPage.getPageable());
    }

    @Override
    @Transactional
    public ResponseDto getInternalSCDetailByInternalSCIdAndStatus(Integer internalSCId, Integer status) {
        InternalSC internalSC = internalSCRepository.findByIdAndEnableAndStatus
                (internalSCId, true, status)
                .orElseThrow(()-> new NotFoundException("Internal supply contract has id: " + internalSCId + " and status: "
                        + status + " not found"));
        List<InternalSCDetail> internalSCDetailList =
                internalSCDetailRepository.findByInternalSCAndEnable(internalSC, true);
        List<InternalSCDetailResponseDto> internalSCDetailResponseDtos = new ArrayList<>();
        Integer serial = 0;
        for (InternalSCDetail element : internalSCDetailList) {
            InternalSCDetailResponseDto internalSCDetailResponseDto = mapperObject.InternalSCDetailEntityToDto(element);
            internalSCDetailResponseDto.setMaterialId(element.getInternalSCDetailId().getMaterialId());
            internalSCDetailResponseDto.setInternalSCId(element.getInternalSCDetailId().getInternalSCId());
            internalSCDetailResponseDto.setMaterialName(element.getMaterial().getName());
//            supplyContractDetailResponseDto.setUnitName(element.getUnit().getName());
            internalSCDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            internalSCDetailResponseDtos.add(internalSCDetailResponseDto);
        }
        return new ResponseDto(HttpStatus.OK.value(), "Successful", internalSCDetailResponseDtos);
    }

    @Override
    @Transactional
    public ResponseDto getInternalSCDetailByID(Integer id) {
        InternalSCDetail internalSCDetail = internalSCDetailRepository.findByInternalSCDetailIdIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        InternalSCDetailResponseDto internalSCDetailResponseDto = mapperObject.InternalSCDetailEntityToDto(internalSCDetail);
        internalSCDetailResponseDto.setMaterialId(internalSCDetail.getInternalSCDetailId().getMaterialId());
        internalSCDetailResponseDto.setInternalSCId(internalSCDetail.getInternalSCDetailId().getInternalSCId());
        internalSCDetailResponseDto.setMaterialName(internalSCDetail.getMaterial().getName());

        internalSCDetailResponseDto.setSerial(1);
//        supplyContractDetailResponseDto.setUnitName(supplyContractDetail.getUnit().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", internalSCDetailResponseDto );
    }
    public ResponseDto deleteInternalSCDetail(Integer id){
        InternalSCDetail internalSCDetail = internalSCDetailRepository.findByInternalSCDetailIdIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id internal supply contract not found!"));
        internalSCDetail.setEnable(false);
        internalSCDetailRepository.save(internalSCDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Delete internal supply contract detail successful", null);
    }

    @Override
    @Transactional
    public ResponseDto getMaxIdInternalSCDetail() {
        Integer idOld = internalSCDetailRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", idOld);
    }

}
