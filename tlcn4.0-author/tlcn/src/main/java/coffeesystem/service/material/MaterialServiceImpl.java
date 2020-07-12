package coffeesystem.service.material;

import coffeesystem.dto.IdNameDto;
import coffeesystem.dto.MaterialDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.repository.*;
import coffeesystem.service.minMaxInventory.MinMaxInventoryService;
import coffeesystem.service.recipe.RecipeService;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    UnitRepository unitRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeService recipeService;
    @Autowired
    MaterialPriceRepository materialPriceRepository;
    @Autowired
    MinMaxInventoryRepository minMaxInventoryRepository;
    @Autowired
    MinMaxInventoryService minMaxInventoryService;
    public ResponseDto createMaterial(MaterialDto materialDto){
        Material material = this.mapperObject.MaterialDtoToEntity1(materialDto);
        MaterialType materialType = materialTypeRepository.findByNameAndEnable(materialDto.getMaterialType(), true)
                .orElseThrow(()-> new NotFoundException("Material type not found"));
        Unit unit = unitRepository.findByNameAndEnable(materialDto.getUnit(), true)
                .orElseThrow(()-> new NotFoundException("Unit not found"));
        material.setMaterialType(materialType);
        material.setUnit(unit);
        materialRepository.save(material);
        return new ResponseDto(HttpStatus.OK.value(), "Create material successful", null);
    }
    @Transactional
    public ResponseDto getAllMaterial(){
        List<Material> materials = materialRepository.findAllByEnable(true);
        List<MaterialDto> materialDtos = new ArrayList<>();
        materials.forEach(element->{
            MaterialDto materialDto = mapperObject.MaterialEntityToDto1(element);
            materialDto.setUnit(element.getUnit().getName());
            materialDto.setMaterialType(element.getMaterialType().getName());
            materialDtos.add(materialDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All material", materialDtos);
    }
    @Transactional
    public ResponseDto getAllMaterialShowIdAndName(){
        List<Material> materials = materialRepository.findAllByEnable(true);
        List<IdNameDto> idNameDtos = new ArrayList<>();
        materials.forEach(element->{
            IdNameDto idNameDto = new IdNameDto();
            idNameDto.setId(element.getId());
            idNameDto.setName(element.getName());
            idNameDtos.add(idNameDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All material", idNameDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllMaterialPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<MaterialDto> materialDtos = new ArrayList<>();
        Page<Material> materialPage = materialRepository.findAllByEnable(true, pageable);
        materialPage.forEach(element->{
            MaterialDto materialDto = mapperObject.MaterialEntityToDto1(element);
            materialDto.setMaterialType(element.getMaterialType().getName());
            materialDto.setUnit(element.getUnit().getName());
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
        MaterialDto materialDto = mapperObject.MaterialEntityToDto1(material);
        materialDto.setUnit(material.getUnit().getName());
        materialDto.setMaterialType(material.getMaterialType().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", materialDto);
    }
    public ResponseDto deleteMaterial(Integer id){
        Material material = materialRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete recipe when material was deleted
        List<Recipe> recipes = recipeRepository.findByMaterialId(id);
        recipes.forEach(element->{
            recipeService.deleteRecipe(element.getRecipeId().getId());
        });

        ///delete material price when material was deleted
        if(materialPriceRepository.findByMaterialPriceIdIdMaterialAndEnable(id, true).isPresent()){
            MaterialPrice materialPrice = materialPriceRepository.findByMaterialPriceIdIdMaterialAndEnable(id, true)
                    .orElseThrow(()-> new NotFoundException("Material price not found"));

            materialPrice.setEnable(false);
            materialPriceRepository.save(materialPrice);
        }
        //delete min max inventory when material was deleted

        List<MinMaxInventory> minMaxInventories = minMaxInventoryRepository.findByMinMaxInventoryIdIdMaterial(id);
        minMaxInventories.forEach(element->{
            minMaxInventoryService.deleteMinMaxInventory(element.getMinMaxInventoryId().getId());
        });
        material.setEnable(false);
        materialRepository.save(material);


        return new ResponseDto(HttpStatus.OK.value(), "Delete material successful", null);
    }
    public ResponseDto editMaterial(MaterialDto materialDto){

        Material material = materialRepository.findByIdAndEnable(materialDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        material.setName(materialDto.getName());
        MaterialType materialType = materialTypeRepository.findByNameAndEnable(materialDto.getMaterialType(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        Unit unit = unitRepository.findByNameAndEnable(materialDto.getUnit(), true)
                .orElseThrow(()-> new NotFoundException("Unit not found"));
        material.setMaterialType(materialType);
        material.setUnit(unit);
        materialRepository.save(material);
        return new ResponseDto(HttpStatus.OK.value(), "Edit material successful", null);
    }
    public ResponseDto getMaxIdMaterial(){
        Integer idOld = materialRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", idOld);
    }

}
