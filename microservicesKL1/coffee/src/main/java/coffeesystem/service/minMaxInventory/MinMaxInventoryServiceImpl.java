package coffeesystem.service.minMaxInventory;

import coffeesystem.dto.*;
import coffeesystem.exception.ExistException;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.MinMaxInventoryId;
import coffeesystem.repository.BranchShopRepository;
import coffeesystem.repository.MaterialRepository;
import coffeesystem.repository.MinMaxInventoryRepository;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinMaxInventoryServiceImpl implements MinMaxInventoryService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    MinMaxInventoryRepository minMaxInventoryRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    public ResponseDto createMinMaxInventory(MinMaxInventoryRequestDto minMaxInventoryRequestDto){
        if(minMaxInventoryRepository.
                findByMinMaxInventoryIdIdMaterialAndMinMaxInventoryIdIdBranchShopAndEnable(minMaxInventoryRequestDto.getMaterialId(),
                        minMaxInventoryRequestDto.getBranchShopId(), true).isPresent()){
            throw new ExistException("Min max inventory was existed");
//            return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
        } else {
            MinMaxInventory minMaxInventory = mapperObject.MinMaxInventoryDtoToEntity(minMaxInventoryRequestDto);
            Material material = materialRepository.findByIdAndEnable(minMaxInventoryRequestDto.getMaterialId(), true)
                    .orElseThrow(()-> new NotFoundException("Material not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(minMaxInventoryRequestDto.getBranchShopId(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop not found"));
            MinMaxInventoryId minMaxInventoryId = new MinMaxInventoryId();
            minMaxInventoryId.setIdMaterial(minMaxInventoryRequestDto.getMaterialId());
            minMaxInventoryId.setIdBranchShop(minMaxInventoryRequestDto.getBranchShopId());
            Integer idOld = minMaxInventoryRepository.findMaxId();
            if(idOld == null){
                idOld = 0;
            }
            minMaxInventoryId.setId(idOld + 1);
            minMaxInventory.setMinMaxInventoryId(minMaxInventoryId);
            minMaxInventory.setMaterial(material);
            minMaxInventory.setBranchShop(branchShop);
            minMaxInventoryRepository.save(minMaxInventory);

            return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
        }

    }
    public ResponseDto editMinMaxInventory(MinMaxInventoryRequestDto minMaxInventoryRequestDto){
        MinMaxInventory minMaxInventory = minMaxInventoryRepository.
                findByMinMaxInventoryIdIdMaterialAndMinMaxInventoryIdIdBranchShopAndEnable(minMaxInventoryRequestDto.getMaterialId(),
                        minMaxInventoryRequestDto.getBranchShopId(), true).orElseThrow(
                ()-> new NotFoundException("Min max inventory not found"));
        minMaxInventory.setMaxInventory(minMaxInventoryRequestDto.getMaxInventory());
        minMaxInventory.setMinInventory(minMaxInventoryRequestDto.getMinInventory());
        minMaxInventoryRepository.save(minMaxInventory);

        return new ResponseDto(HttpStatus.OK.value(), "Edit successful", null);
    }
    @Transactional
    public ResponseDto getAllMinMaxInventory(){
        List<MinMaxInventory> minMaxInventoryList = this.minMaxInventoryRepository.findAllByEnable(true);
        List<MinMaxInventoryResponseDto> minMaxInventoryResponseDtos = new ArrayList<>();
        minMaxInventoryList.forEach(element->{
            MinMaxInventoryResponseDto minMaxInventoryResponseDto = mapperObject.MinMaxInventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getMinMaxInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getMinMaxInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            minMaxInventoryResponseDto.setId(element.getMinMaxInventoryId().getId());
            minMaxInventoryResponseDto.setMaterialId(element.getMinMaxInventoryId().getIdMaterial());
            minMaxInventoryResponseDto.setBranchShopId(element.getMinMaxInventoryId().getIdBranchShop());
            minMaxInventoryResponseDto.setBranchShopName(branchShop.getName());
            minMaxInventoryResponseDto.setMaterialName(material.getName());
            minMaxInventoryResponseDto.setUnitName(material.getUnit().getName());
            minMaxInventoryResponseDtos.add(minMaxInventoryResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All min max inventory", minMaxInventoryResponseDtos);
    }
    @Transactional
    public PagingResponseDto getAllMinMaxInventoryPaging(int page, int size, String sort, String sortColumn){

        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<MinMaxInventoryResponseDto> minMaxInventoryResponseDtos = new ArrayList<>();
        Page<MinMaxInventory> minMaxInventoryPage = minMaxInventoryRepository.findAllByEnable(true, pageable);

        minMaxInventoryPage.forEach(element->{
            MinMaxInventoryResponseDto minMaxInventoryResponseDto = mapperObject.MinMaxInventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getMinMaxInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getMinMaxInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            minMaxInventoryResponseDto.setId(element.getMinMaxInventoryId().getId());
            minMaxInventoryResponseDto.setMaterialId(element.getMinMaxInventoryId().getIdMaterial());
            minMaxInventoryResponseDto.setBranchShopId(element.getMinMaxInventoryId().getIdBranchShop());
            minMaxInventoryResponseDto.setBranchShopName(branchShop.getName());
            minMaxInventoryResponseDto.setMaterialName(material.getName());
            minMaxInventoryResponseDto.setUnitName(material.getUnit().getName());
            minMaxInventoryResponseDtos.add(minMaxInventoryResponseDto);});
        Page<MinMaxInventoryResponseDto> minMaxInventoryResponseDtoPage = new PageImpl<>(minMaxInventoryResponseDtos, pageable,
                minMaxInventoryPage.getTotalElements());
        return new PagingResponseDto<>(
                minMaxInventoryResponseDtoPage.getContent(), minMaxInventoryResponseDtoPage.getTotalElements(),
                minMaxInventoryResponseDtoPage.getTotalPages(), minMaxInventoryResponseDtoPage.getPageable());

    }
    @Transactional
    public PagingResponseDto getAllByBranchShopIdPaging(int page, int size, String sort,
                                                        String sortColumn, Integer branchShopId){

        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<MinMaxInventoryResponseDto> minMaxInventoryResponseDtos = new ArrayList<>();
        Page<MinMaxInventory> minMaxInventoryPage = minMaxInventoryRepository.findByMinMaxInventoryIdIdBranchShopAndEnable(branchShopId, true, pageable);

        minMaxInventoryPage.forEach(element->{
            MinMaxInventoryResponseDto minMaxInventoryResponseDto = mapperObject.MinMaxInventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getMinMaxInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getMinMaxInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            minMaxInventoryResponseDto.setId(element.getMinMaxInventoryId().getId());
            minMaxInventoryResponseDto.setMaterialId(element.getMinMaxInventoryId().getIdMaterial());
            minMaxInventoryResponseDto.setBranchShopId(element.getMinMaxInventoryId().getIdBranchShop());
            minMaxInventoryResponseDto.setBranchShopName(branchShop.getName());
            minMaxInventoryResponseDto.setMaterialName(material.getName());
            minMaxInventoryResponseDto.setUnitName(material.getUnit().getName());
            minMaxInventoryResponseDtos.add(minMaxInventoryResponseDto);});
        Page<MinMaxInventoryResponseDto> minMaxInventoryResponseDtoPage = new PageImpl<>(minMaxInventoryResponseDtos, pageable,
                minMaxInventoryPage.getTotalElements());
        return new PagingResponseDto<>(
                minMaxInventoryResponseDtoPage.getContent(), minMaxInventoryResponseDtoPage.getTotalElements(),
                minMaxInventoryResponseDtoPage.getTotalPages(), minMaxInventoryResponseDtoPage.getPageable());

    }
    @Transactional
    public ResponseDto getMinMaxByIdMaterialAndIdBranchShop(Integer materialId, Integer branchShopId){
        MinMaxInventory minMaxInventory = minMaxInventoryRepository.
                findByMinMaxInventoryIdIdMaterialAndMinMaxInventoryIdIdBranchShopAndEnable(materialId, branchShopId, true)
                .orElseThrow(()-> new NotFoundException("Min max inventory not found"));
        MinMaxInventoryResponseDto minMaxInventoryResponseDto = mapperObject.MinMaxInventoryEntityToDto(minMaxInventory);
        minMaxInventoryResponseDto.setId(minMaxInventory.getMinMaxInventoryId().getId());
        minMaxInventoryResponseDto.setMaterialId(minMaxInventory.getMinMaxInventoryId().getIdMaterial());
        minMaxInventoryResponseDto.setBranchShopId(minMaxInventory.getMinMaxInventoryId().getIdBranchShop());
        minMaxInventoryResponseDto.setBranchShopName(minMaxInventory.getBranchShop().getName());
        minMaxInventoryResponseDto.setMaterialName(minMaxInventory.getMaterial().getName());
        minMaxInventoryResponseDto.setUnitName(minMaxInventory.getMaterial().getUnit().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", minMaxInventoryResponseDto);
    }
    public ResponseDto deleteMinMaxInventory(Integer materialId, Integer branchShopId){
        MinMaxInventory minMaxInventory = minMaxInventoryRepository.
                findByMinMaxInventoryIdIdMaterialAndMinMaxInventoryIdIdBranchShopAndEnable(materialId, branchShopId, true)
                .orElseThrow(()-> new NotFoundException("Min max inventory not found"));

        minMaxInventory.setEnable(false);
        minMaxInventoryRepository.save(minMaxInventory);
        return new ResponseDto(HttpStatus.OK.value(), "Delete min max inventory successful", null);
    }

}
