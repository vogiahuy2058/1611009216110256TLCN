package coffeesystem.service.drinkType;

import coffeesystem.dto.DrinkTypeDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Drink;
import coffeesystem.model.DrinkType;
import coffeesystem.repository.DrinkRepository;
import coffeesystem.repository.DrinkTypeRepository;
import coffeesystem.service.drink.DrinkService;
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
public class DrinkTypeServiceImpl implements DrinkTypeService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    DrinkTypeRepository drinkTypeRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    DrinkService drinkService;
    public ResponseDto createDrinkType(DrinkTypeDto drinkTypeDto){
        DrinkType drinkType = this.mapperObject.DrinkTypeDtoToEntity(drinkTypeDto);
        drinkTypeRepository.save(drinkType);
        return new ResponseDto(HttpStatus.OK.value(), "Create drink type successful", null);
    }
    @Transactional
    public ResponseDto getAllDrinkType(){
        List<DrinkType> drinkTypes = drinkTypeRepository.findAllByEnable(true);
        List<DrinkTypeDto> drinkTypeDtos = new ArrayList<>();
        drinkTypes.forEach(element->{
            DrinkTypeDto drinkTypeDto =  mapperObject.DrinkTypeEntityToDto(element);
            drinkTypeDtos.add(drinkTypeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All drink type", drinkTypeDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllDrinkTypePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<DrinkTypeDto> drinkTypeDtos = new ArrayList<>();
        Page<DrinkType> drinkTypePage = drinkTypeRepository.findAllByEnable(true, pageable);
        drinkTypePage.forEach(element->{
            DrinkTypeDto drinkTypeDto =  mapperObject.DrinkTypeEntityToDto(element);
            drinkTypeDtos.add(drinkTypeDto);});
        Page<DrinkTypeDto> drinkTypeDtoPage = new PageImpl<>(drinkTypeDtos, pageable,
                drinkTypePage.getTotalElements() );
        return new PagingResponseDto<>(
                drinkTypeDtoPage.getContent(), drinkTypeDtoPage.getTotalElements(), drinkTypeDtoPage.getTotalPages(),
                drinkTypeDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getDrinkTypeById(Integer id){
        DrinkType drinkType = drinkTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        DrinkTypeDto drinkTypeDto = mapperObject.DrinkTypeEntityToDto(drinkType);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", drinkTypeDto);
    }
    public ResponseDto deleteDrinkType(Integer id){
        DrinkType drinkType = drinkTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
       //delete drink when drink type was delete
        List<Drink> drinkList = drinkRepository.findByDrinkTypeIdAndEnable(id, true);
        drinkList.forEach(element->{
            drinkService.deleteDrink(element.getId());
        });
        drinkType.setEnable(false);
        drinkTypeRepository.save(drinkType);
        return new ResponseDto(HttpStatus.OK.value(),
                "Delete drink type successful", null);
    }
    public ResponseDto editDrinkType(DrinkTypeDto drinkTypeDto){
        DrinkType drinkType = drinkTypeRepository.findByIdAndEnable(drinkTypeDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        drinkType.setName(drinkTypeDto.getName());
        drinkTypeRepository.save(drinkType);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink type successful", null);
    }
}
