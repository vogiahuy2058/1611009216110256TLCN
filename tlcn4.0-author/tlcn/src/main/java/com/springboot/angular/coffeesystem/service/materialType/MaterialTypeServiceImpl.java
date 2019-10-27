package com.springboot.angular.coffeesystem.service.materialType;

import com.springboot.angular.coffeesystem.dto.MaterialTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.MaterialType;
import com.springboot.angular.coffeesystem.repository.MaterialRepository;
import com.springboot.angular.coffeesystem.repository.MaterialTypeRepository;
import com.springboot.angular.coffeesystem.service.material.MaterialService;
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
public class MaterialTypeServiceImpl implements MaterialTypeService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    MaterialTypeRepository materialTypeRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialService materialService;
    public ResponseDto createMaterialType(MaterialTypeDto materialTypeDto){
        MaterialType materialType = this.mapperObject.MaterialTypeDtoToEntity(materialTypeDto);
        materialTypeRepository.save(materialType);
        return new ResponseDto(HttpStatus.OK.value(), "create material type successful", null);
    }
    @Transactional
    public ResponseDto getAllMaterialType(){
        List<MaterialType> materialTypes = materialTypeRepository.findAllByEnable(true);
        List<MaterialTypeDto> materialTypeDtos = new ArrayList<>();
        materialTypes.forEach(element->{
            MaterialTypeDto materialTypeDto = mapperObject.MaterialTypeEntityToDto(element);
            materialTypeDtos.add(materialTypeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All material type", materialTypeDtos);
    }

    @Transactional
    @Override
    public PagingResponseDto getAllMaterialTypePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<MaterialTypeDto> materialTypeDtos = new ArrayList<>();
        Page<MaterialType> materialTypePage = materialTypeRepository.findAllByEnable(true, pageable);
        materialTypePage.forEach(element->{
            MaterialTypeDto materialTypeDto = mapperObject.MaterialTypeEntityToDto(element);
            materialTypeDtos.add(materialTypeDto);});
        Page<MaterialTypeDto> materialTypeDtoPage = new PageImpl<>(materialTypeDtos, pageable,
                materialTypePage.getTotalElements() );
        return new PagingResponseDto<>(
                materialTypeDtoPage.getContent(), materialTypeDtoPage.getTotalElements(), materialTypeDtoPage.getTotalPages(),
                materialTypeDtoPage.getPageable());
    }
    public ResponseDto editMaterialType(MaterialTypeDto materialTypeDto){
        MaterialType materialType = materialTypeRepository.findByIdAndEnable(materialTypeDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        materialType.setName(materialTypeDto.getName());
        materialTypeRepository.save(materialType);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink type successful", null);
    }
    @Transactional
    public ResponseDto getMaterialTypeById(Integer id){
        MaterialType materialType = materialTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        MaterialTypeDto materialTypeDto = mapperObject.MaterialTypeEntityToDto(materialType);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", materialTypeDto);
    }
    public ResponseDto deleteMaterialType(Integer id){
        MaterialType materialType = materialTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete material when material type was deleted
        List<Material> materials = materialRepository.findByMaterialTypeId(id);
        materials.forEach(element->{
            materialService.deleteMaterial(element.getId());
        });
        materialType.setEnable(false);
        materialTypeRepository.save(materialType);
        return new ResponseDto(HttpStatus.OK.value(), "Delete material type successful", null);
    }
}
