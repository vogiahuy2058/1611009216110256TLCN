package com.springboot.angular.coffeesystem.service.materialPrice;

import com.springboot.angular.coffeesystem.dto.MaterialPriceDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Material;
import com.springboot.angular.coffeesystem.model.MaterialPrice;
import com.springboot.angular.coffeesystem.model.embedding.DrinkPriceId;
import com.springboot.angular.coffeesystem.model.embedding.MaterialPriceId;
import com.springboot.angular.coffeesystem.repository.MaterialPriceRepository;
import com.springboot.angular.coffeesystem.repository.MaterialRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MaterialPriceServiceImpl implements MaterialPriceService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    MaterialPriceRepository materialPriceRepository;
    @Autowired
    MaterialRepository materialRepository;
    public ResponseDto createPriceOfMaterial(MaterialPriceDto materialPriceDto){
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        MaterialPrice materialPrice = mapperObject.MaterialPriceDtoToEntity(materialPriceDto);
        Material material = materialRepository.findByIdAndEnable(materialPriceDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        MaterialPriceId materialPriceId = new MaterialPriceId();
        materialPriceId.setId(materialPriceDto.getMaterialId());
        materialPriceId.setDate(LocalDate.parse(materialPriceDto.getDate(), dtf));
        materialPrice.setMaterialPriceId(materialPriceId);
        materialPrice.setMaterial(material);
        materialPriceRepository.save(materialPrice);
        return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
    }
    public ResponseDto changePriceOfMaterial(MaterialPriceDto materialPriceDto){
        MaterialPrice materialPrice = materialPriceRepository.findByMaterialPriceIdIdAndEnable(materialPriceDto.getMaterialId(), true)
                .orElseThrow(()-> new NotFoundException("Id material not found"));
        materialPrice.setEnable(false);
        materialPriceRepository.save(materialPrice);

        createPriceOfMaterial(materialPriceDto);
        return new ResponseDto(HttpStatus.OK.value(), "Change price successful", null);
    }
    @Transactional
    public ResponseDto getPriceOfMaterial(Integer materialId){
        MaterialPrice materialPrice = materialPriceRepository.findByMaterialPriceIdIdAndEnable(materialId, true)
                .orElseThrow(()-> new NotFoundException("Id material not found"));
        MaterialPriceDto materialPriceDto = mapperObject.MaterialPriceEntityToDto(materialPrice);
        materialPriceDto.setMaterialId(materialPrice.getMaterialPriceId().getId());
        materialPriceDto.setDate(materialPrice.getMaterialPriceId().getDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return new ResponseDto(HttpStatus.OK.value(), "Successful", materialPriceDto);
    }
}
