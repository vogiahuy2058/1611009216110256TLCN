package com.springboot.angular.coffeesystem.service.material;

import com.springboot.angular.coffeesystem.dto.MaterialDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.MaterialType;
import com.springboot.angular.coffeesystem.model.Recipe;
import com.springboot.angular.coffeesystem.repository.MaterialRepository;
import com.springboot.angular.coffeesystem.repository.MaterialTypeRepository;
import com.springboot.angular.coffeesystem.repository.RecipeRepository;
import com.springboot.angular.coffeesystem.service.recipe.RecipeService;
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
@Transactional
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialTypeRepository materialTypeRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeService recipeService;
    public ResponseDto createMaterial(MaterialDto materialDto){
        Material material = this.mapperObject.MaterialDtoToEntity(materialDto);
        MaterialType materialType = materialTypeRepository.findByNameAndEnable(materialDto.getMaterialType(), true)
                .orElseThrow(()-> new NotFoundException("Material type not found"));
        material.setMaterialType(materialType);
        materialRepository.save(material);
        return new ResponseDto(HttpStatus.OK.value(), "Create material successful", null);
    }
    @Transactional
    public ResponseDto getAllMaterial(){
        List<Material> materials = materialRepository.findAllByEnable(true);
        List<MaterialDto> materialDtos = new ArrayList<>();
        materials.forEach(element->{
            MaterialDto materialDto = mapperObject.MaterialEntityToDto(element);

            materialDto.setMaterialType(element.getMaterialType().getName());
            materialDtos.add(materialDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All material", materialDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllMaterialPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<MaterialDto> materialDtos = new ArrayList<>();
        Page<Material> materialPage = materialRepository.findAllByEnable(true, pageable);
        materialPage.forEach(element->{
            MaterialDto materialDto = mapperObject.MaterialEntityToDto(element);
            materialDto.setMaterialType(element.getMaterialType().getName());
            materialDtos.add(materialDto);});
        Page<MaterialDto> materialDtoPage = new PageImpl<>(materialDtos, pageable,
                materialPage.getTotalElements() );
        return new PagingResponseDto<>(
                materialDtoPage.getContent(), materialDtoPage.getTotalElements(), materialDtoPage.getTotalPages(),
                materialDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getMaterialById(Integer id){
        Material material = materialRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        MaterialDto materialDto = mapperObject.MaterialEntityToDto(material);
        materialDto.setMaterialType(material.getMaterialType().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", materialDto);
    }
    public ResponseDto deleteMaterial(Integer id){
        Material material = materialRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete recipe when material was deleted
        List<Recipe> recipes = recipeRepository.findByMaterialId(id);
        recipes.forEach(element->{
            recipeService.deleteRecipe(element.getDrink().getId(),
                    element.getMaterial().getId());
        });
        material.setEnable(false);
        materialRepository.save(material);


        return new ResponseDto(HttpStatus.OK.value(), "Delete material successful", null);
    }
    public ResponseDto editMaterial(MaterialDto materialDto){

        Material material = materialRepository.findByIdAndEnable(materialDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        material.setName(materialDto.getName());
        material.setInventory(materialDto.getInventory());
        material.setMinInventory(materialDto.getMinInventory());
        material.setMaxInventory(materialDto.getMaxInventory());
        MaterialType materialType = materialTypeRepository.findByNameAndEnable(materialDto.getMaterialType(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        material.setMaterialType(materialType);
        materialRepository.save(material);
        return new ResponseDto(HttpStatus.OK.value(), "Edit material successful", null);
    }
//    public ResponseDto changePrice(Integer id, float newPrice){
//        Material material = materialRepository.findByMaterialIdIdAndEnable(id, true)
//                .orElseThrow(()-> new NotFoundException("Material not found"));
//        material.setEnable(false);
//        materialRepository.save(material);
//
//        MaterialDto materialDto = mapperObject.MaterialEntityToDto(material);
//        materialDto.setId(material.getMaterialId().getId());
//        materialDto.setDate(LocalDate.now());
//        materialDto.setMaterialType(material.getMaterialType().getName());
//        materialDto.setPrice(newPrice);
//        createMaterial(materialDto);
//        return new ResponseDto(HttpStatus.OK.value(), "Price is changed successfully", null);
//    }


}
