package coffeesystem.service.InternalSC;

import coffeesystem.dto.*;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.BranchShop;
import coffeesystem.model.InternalSC;
import coffeesystem.repository.BranchShopRepository;
import coffeesystem.repository.InternalSCRepository;
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
public class InternalSCServiceImpl implements InternalSCService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    InternalSCRepository internalSCRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ResponseDto createInternalSC(InternalSCRequestDto internalSCRequestDto){
        InternalSC internalSC = this.mapperObject.InternalSCDtoToEntity(internalSCRequestDto);
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(internalSCRequestDto.getBranchShop(),
                true).orElseThrow(()-> new NotFoundException("Branch shop not found"));
        internalSC.setBranchShop(branchShop);
        internalSC.setStatus(0);
        internalSCRepository.save(internalSC);
        return new ResponseDto(HttpStatus.OK.value(), "Create internal supply contract successful", null);
    }
    @Transactional
    public ResponseDto getAllInternalSC(){
        List<InternalSC> internalSCS = internalSCRepository.findAllByEnable(true);
        List<InternalSCResponseDto> internalSCResponseDtos = new ArrayList<>();
        internalSCS.forEach(element->{
            InternalSCResponseDto internalSCResponseDto =
                    mapperObject.InternalSCEntityToDto(element);
            internalSCResponseDto.setDate(element.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
            internalSCResponseDtos.add(internalSCResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All internal supply contract", internalSCResponseDtos);
    }

    @Transactional
    public ResponseDto getInternalSCById(Integer id){
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        InternalSCResponseDto internalSCResponseDto =
                mapperObject.InternalSCEntityToDto(internalSC);
        internalSCResponseDto.setDate(internalSC.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        internalSCResponseDto.setDeliveryTime(internalSC.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        internalSCResponseDto.setBranchShop(internalSC.getBranchShop().getName());
        return new ResponseDto(HttpStatus.OK.value(), "Get internalsupply contract by id: "+id, internalSCResponseDto);
    }

    @Transactional
    public PagingResponseDto getAllInternalSCPaging(int page, int size, String sort, String sortColumn){
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InternalSCResponseDto> internalSCResponseDtos = new ArrayList<>();
        Page<InternalSC> internalSCPage = internalSCRepository.findAllByEnable(true, pageable);
        internalSCPage.forEach(element->{
            InternalSCResponseDto internalSCResponseDto =
                    mapperObject.InternalSCEntityToDto(element);
            internalSCResponseDto.setDate(element.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
            internalSCResponseDtos.add(internalSCResponseDto);});
        Page<InternalSCResponseDto> internalSCResponseDtoPage = new PageImpl<>(internalSCResponseDtos, pageable,
                internalSCPage.getTotalElements() );
        return new PagingResponseDto<>(
                internalSCResponseDtoPage.getContent(), internalSCResponseDtoPage.getTotalElements(),
                internalSCResponseDtoPage.getTotalPages(), internalSCResponseDtoPage.getPageable());
    }

    @Transactional
    public ResponseDto getAllInternalSCDateToDate(String fromDate, String toDate){
        LocalDate newFromDate = LocalDate.parse(fromDate, dtf);
        LocalDate newToDate = LocalDate.parse(toDate, dtf);
        List<InternalSC> internalSCS = internalSCRepository.findAllByEnable(true);
        List<InternalSCResponseDto> internalSCResponseDtos = new ArrayList<>();
        internalSCS.forEach(element->{
            LocalDate internalSCDate = element.getDateCreate();
            if(internalSCDate.isBefore(newToDate) && internalSCDate.isAfter(newFromDate)){
                InternalSCResponseDto internalSCResponseDto =
                        mapperObject.InternalSCEntityToDto(element);
                internalSCResponseDto.setDate(element.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
                internalSCResponseDtos.add(internalSCResponseDto);
            }

        });
        return new ResponseDto(HttpStatus.OK.value(), "All internal supply contract date to date", internalSCResponseDtos);
    }
    public ResponseDto deleteInternalSC(Integer id){
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        internalSC.setEnable(false);
        internalSCRepository.save(internalSC);
        return new ResponseDto(HttpStatus.OK.value(), "Delete internal supply contract successful", null);
    }

    public ResponseDto editInternalSC(InternalSCRequestDto internalSCRequestDto){
        InternalSC internalSC = internalSCRepository.findByIdAndEnable(internalSCRequestDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        BranchShop branchShop = branchShopRepository.findByNameAndEnable(internalSCRequestDto.getBranchShop(),
                true).orElseThrow(()-> new NotFoundException("Branch shop not found"));
        internalSC.setBranchShop(branchShop);
        internalSC.setDateCreate(internalSCRequestDto.getDate());
        internalSC.setDeliveryTime(internalSCRequestDto.getDeliveryTime());
        internalSC.setStatus(internalSCRequestDto.getStatus());
        internalSCRepository.save(internalSC);
        return new ResponseDto(HttpStatus.OK.value(), "Edit internal supply contract successful", null);
    }

}
