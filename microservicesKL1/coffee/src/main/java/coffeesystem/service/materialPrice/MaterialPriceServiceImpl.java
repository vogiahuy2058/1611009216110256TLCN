package coffeesystem.service.materialPrice;

import coffeesystem.dto.MaterialPriceRequestDto;
import coffeesystem.dto.MaterialPriceResponseDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Material;
import coffeesystem.model.MaterialPrice;
import coffeesystem.model.embedding.MaterialPriceId;
import coffeesystem.repository.MaterialPriceRepository;
import coffeesystem.repository.MaterialRepository;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
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
public class MaterialPriceServiceImpl implements MaterialPriceService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    MaterialPriceRepository materialPriceRepository;
    @Autowired
    MaterialRepository materialRepository;
    public ResponseDto createPriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto){

        //neu material price do da ton tai thì enable=false
        if(materialPriceRepository.findByMaterialPriceIdIdMaterialAndEnable(materialPriceRequestDto.getMaterialId(),
                true).isPresent()){
            MaterialPrice materialPriceOld = materialPriceRepository.findByMaterialPriceIdIdMaterialAndEnable(materialPriceRequestDto.getMaterialId(), true)
                    .orElseThrow(()-> new NotFoundException("Id material not found"));
            materialPriceOld.setEnable(false);
            materialPriceRepository.save(materialPriceOld);
        }

        MaterialPrice materialPrice = mapperObject.MaterialPriceDtoToEntity(materialPriceRequestDto);
        Material material = materialRepository.findByIdAndEnable(materialPriceRequestDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        MaterialPriceId materialPriceId = new MaterialPriceId();
        materialPriceId.setIdMaterial(materialPriceRequestDto.getMaterialId());
        Integer idOld = materialPriceRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        materialPriceId.setId(idOld + 1);
        materialPriceId.setFirstDate(materialPriceRequestDto.getFirstDate());
        materialPrice.setMaterialPriceId(materialPriceId);
        materialPrice.setMaterial(material);
        materialPriceRepository.save(materialPrice);

        return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
    }
    public ResponseDto changePriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto){
//        MaterialPrice materialPrice =
//                materialPriceRepository.findByMaterialPriceIdIdMaterialAndEnable(materialPriceRequestDto.getMaterialId(), true)
//                .orElseThrow(()-> new NotFoundException("Id material not found"));
//        materialPrice.setEnable(false);
//        materialPriceRepository.save(materialPrice);

        createPriceOfMaterial(materialPriceRequestDto);
        return new ResponseDto(HttpStatus.OK.value(), "Change price successful", null);
    }
    @Transactional
    public ResponseDto getPriceOfMaterial(Integer materialId){
        MaterialPrice materialPrice = materialPriceRepository.findByMaterialPriceIdIdMaterialAndEnable(materialId, true)
                .orElseThrow(()-> new NotFoundException("Id material not found"));
        Material material = materialRepository.findByIdAndEnable(materialId, true)
                .orElseThrow(()-> new NotFoundException("Material id not found"));
        MaterialPriceResponseDto materialPriceResponseDto = mapperObject.MaterialPriceEntityToDto1(materialPrice);
        materialPriceResponseDto.setId(materialPrice.getMaterialPriceId().getId());
        materialPriceResponseDto.setMaterialId(materialPrice.getMaterialPriceId().getIdMaterial());
        materialPriceResponseDto.setFirstDate(materialPrice.getMaterialPriceId().getFirstDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        materialPriceResponseDto.setLastDate(materialPrice.getLastDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        materialPriceResponseDto.setMaterialName(material.getName());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", materialPriceResponseDto);
    }
    @Transactional
    public ResponseDto getAllPriceOfMaterial(){
        List<MaterialPrice> materialPriceList = this.materialPriceRepository.findAllByEnable(true);
        List<MaterialPriceResponseDto> materialPriceResponseDtos = new ArrayList<>();
        materialPriceList.forEach(element->{
            MaterialPriceResponseDto materialPriceResponseDto = mapperObject.MaterialPriceEntityToDto1(element);
            Material material = materialRepository.findByIdAndEnable(element.getMaterialPriceId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            materialPriceResponseDto.setId(element.getMaterialPriceId().getId());
            materialPriceResponseDto.setMaterialId(element.getMaterialPriceId().getIdMaterial());
            materialPriceResponseDto.setFirstDate(element.getMaterialPriceId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            materialPriceResponseDto.setLastDate(element.getLastDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            materialPriceResponseDto.setMaterialName(material.getName());
            materialPriceResponseDtos.add(materialPriceResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All material price", materialPriceResponseDtos);
    }
    @Transactional
    public PagingResponseDto getAllMaterialPricePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<MaterialPriceResponseDto> materialPriceResponseDtos = new ArrayList<>();
        Page<MaterialPrice> materialPricePage = materialPriceRepository.findAllByEnable(true, pageable);

        materialPricePage.forEach(element->{
            MaterialPriceResponseDto materialPriceResponseDto = mapperObject.MaterialPriceEntityToDto1(element);
            Material material = materialRepository.findByIdAndEnable(element.getMaterialPriceId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            materialPriceResponseDto.setId(element.getMaterialPriceId().getId());
            materialPriceResponseDto.setMaterialId(element.getMaterialPriceId().getIdMaterial());
            materialPriceResponseDto.setFirstDate(element.getMaterialPriceId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            materialPriceResponseDto.setLastDate(element.getLastDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            materialPriceResponseDto.setMaterialName(material.getName());
            materialPriceResponseDtos.add(materialPriceResponseDto);});
        Page<MaterialPriceResponseDto> materialPriceResponseDtoPage = new PageImpl<>(materialPriceResponseDtos, pageable,
                materialPricePage.getTotalElements());
        return new PagingResponseDto<>(
                materialPriceResponseDtoPage.getContent(), materialPriceResponseDtoPage.getTotalElements(), materialPriceResponseDtoPage.getTotalPages(),
                materialPriceResponseDtoPage.getPageable());
    }
}
