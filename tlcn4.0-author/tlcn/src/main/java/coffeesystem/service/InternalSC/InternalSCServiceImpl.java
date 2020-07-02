package coffeesystem.service.InternalSC;

import coffeesystem.dto.*;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.repository.BranchShopRepository;
import coffeesystem.repository.InternalSCDetailRepository;
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
    @Autowired
    InternalSCDetailRepository internalSCDetailRepository;
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
//            internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
            if(element.getDeliveryTime() == null){
                internalSCResponseDto.setDeliveryTime("null");
            }
            else {
                internalSCResponseDto.setDeliveryTime(element.getDeliveryTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
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
//        internalSCResponseDto.setDeliveryTime(internalSC.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        internalSCResponseDto.setBranchShop(internalSC.getBranchShop().getName());
        if(internalSC.getDeliveryTime() == null){
            internalSCResponseDto.setDeliveryTime("null");
        }
        else {
            internalSCResponseDto.setDeliveryTime(internalSC.getDeliveryTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        return new ResponseDto(HttpStatus.OK.value(), "Get internalsupply contract by id: "+id, internalSCResponseDto);
    }
    @Transactional
    public ResponseDto getAllInternalSCByBranchShopAndStatus(Integer idBranchShop, Integer status){
        List<InternalSC> internalSCS = internalSCRepository.findByBranchShopIdAndStatusAndEnable(
                idBranchShop, status,true);
        List<InternalSCResponseDto> internalSCResponseDtos = new ArrayList<>();
        internalSCS.forEach(element->{
            InternalSCResponseDto internalSCResponseDto =
                    mapperObject.InternalSCEntityToDto(element);
            internalSCResponseDto.setDate(element.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
            if(element.getDeliveryTime() == null){
                internalSCResponseDto.setDeliveryTime("null");
            }
            else {
                internalSCResponseDto.setDeliveryTime(element.getDeliveryTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            internalSCResponseDtos.add(internalSCResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All internal supply contract", internalSCResponseDtos);
    }

    @Transactional
    public ResponseDto getInternalSCHaveMaxIdByIdBranchShopAndStatus(Integer idBranchShop, Integer status){
//        InternalSC internalSC = internalSCRepository
//                .findInternalSCHaveMaxIdByBranchShopIdAndEnableAndStatus(idBranchShop, true, status)
//                .orElseThrow(()->
//                        new NotFoundException("Not found internal supply contract have id branch shop = " +
//                                idBranchShop + " and status = " + status));
        Integer maxId = internalSCRepository.findMaxIdByBranchShopIdAndEnableAndStatus(idBranchShop,true, status);

        InternalSC internalSC = internalSCRepository.findByIdAndEnable(maxId, true)
                .orElse(null);
        List<InternalSC> internalSCList = new ArrayList<>();
        List<InternalSCResponseDto> internalSCResponseDtos = new ArrayList<>();
        if(internalSC == null){
            return new ResponseDto(HttpStatus.OK.value(), "Internal supply contract have id branch shop = " +
                    idBranchShop + " and status = " + status, null);

        }else {
            internalSCList.add(internalSC);
            internalSCList.forEach(element->{
                InternalSCResponseDto internalSCResponseDto =
                        mapperObject.InternalSCEntityToDto(element);
                internalSCResponseDto.setDate(element.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//                internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
                if(element.getDeliveryTime() == null){
                    internalSCResponseDto.setDeliveryTime("null");
                }
                else {
                    internalSCResponseDto.setDeliveryTime(element.getDeliveryTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                internalSCResponseDtos.add(internalSCResponseDto);
            });
            return new ResponseDto(HttpStatus.OK.value(), "Internal supply contract have id branch shop = " +
                    idBranchShop + " and status = " + status, internalSCResponseDtos);
        }


//        InternalSCResponseDto internalSCResponseDto =
//                mapperObject.InternalSCEntityToDto(internalSC);
//        internalSCResponseDto.setDate(internalSC.getDateCreate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        internalSCResponseDto.setDeliveryTime(internalSC.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        internalSCResponseDto.setBranchShop(internalSC.getBranchShop().getName());

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
//            internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
            if(element.getDeliveryTime() == null){
                internalSCResponseDto.setDeliveryTime("null");
            }
            else {
                internalSCResponseDto.setDeliveryTime(element.getDeliveryTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
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
//                internalSCResponseDto.setDeliveryTime(element.getDeliveryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                internalSCResponseDto.setBranchShop(element.getBranchShop().getName());
                if(element.getDeliveryTime() == null){
                    internalSCResponseDto.setDeliveryTime("null");
                }
                else {
                    internalSCResponseDto.setDeliveryTime(element.getDeliveryTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                internalSCResponseDtos.add(internalSCResponseDto);
            }

        });
        return new ResponseDto(HttpStatus.OK.value(), "All internal supply contract date to date", internalSCResponseDtos);
    }

    @Override
    @Transactional
    public ResponseDto getMaxIdInternalSC() {
        Integer idOld = internalSCRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", idOld);
    }
    @Transactional
    public ResponseDto getTotalNumberOfRequestAndTotalQuantityAllowMaterial(List<InternalSCRequestDto1> internalSCRequestDto1s){
        List<MaterialDto1> materialDto1List = new ArrayList<>();
        internalSCRequestDto1s.forEach(internalSCRequestDto1 -> {
            //dua tren id tim ra hđcc noi bo
            InternalSC internalSC = internalSCRepository.findByIdAndEnable(internalSCRequestDto1.getId(), true)
                    .orElseThrow(()-> new NotFoundException("Internal supply contract have id = "
                    + internalSCRequestDto1.getId() + " not found"));
            //lay chi tiet hdcc noi bo cua hdcc do
            List<InternalSCDetail> internalSCDetailList = internalSCDetailRepository
                    .findByInternalSCAndEnableOrderByLastModifiedDateDesc(internalSC, true);

            internalSCDetailList.forEach(internalSCDetail -> {
                if(materialDto1List.size() == 0){
                    MaterialDto1 materialDto1New = new MaterialDto1();
                    materialDto1New.setId(internalSCDetail.getInternalSCDetailId().getMaterialId());
                    materialDto1New.setName(internalSCDetail.getMaterial().getName());
                    materialDto1New.setTotalNumberOfRequest(internalSCDetail.getNumberOfRequest());
                    materialDto1New.setTotalQuantityAllow(internalSCDetail.getQuantityAllowed());
                    materialDto1List.add(materialDto1New);
                }else {
                    boolean coTimRa = false;
                    for(int ii = 0; ii<materialDto1List.size(); ii++){
                        //voi moi nguyen lieu trong chi tiet hop dong cung cap,
                        // lay id nguyen lieu do ra so sanh voi moi nguyen lieu trong materialDto1List
                        //neu nguyen lieu do da co trong materialDto1List:cong don totalNumberOfReques
                        //nguoc lai add 1 materialDto1 vao materialDto1List
                        if (materialDto1List.get(ii).getId() == internalSCDetail.getInternalSCDetailId().getMaterialId()){
                            float oldTotalAllow = materialDto1List.get(ii).getTotalQuantityAllow();
                            float oldTotalRequest = materialDto1List.get(ii).getTotalNumberOfRequest();
                            materialDto1List.get(ii).setTotalQuantityAllow(oldTotalAllow + internalSCDetail.getQuantityAllowed());
                            materialDto1List.get(ii).setTotalNumberOfRequest(oldTotalRequest + internalSCDetail.getNumberOfRequest());
                            coTimRa = true;
                            break;
                        }
                    }
                    if(coTimRa == false){
                        MaterialDto1 materialDto1New = new MaterialDto1();
                        materialDto1New.setId(internalSCDetail.getInternalSCDetailId().getMaterialId());
                        materialDto1New.setName(internalSCDetail.getMaterial().getName());
                        materialDto1New.setTotalQuantityAllow(internalSCDetail.getQuantityAllowed());
                        materialDto1New.setTotalNumberOfRequest(internalSCDetail.getNumberOfRequest());
                        materialDto1List.add(materialDto1New);
                    }
                }



            });

        });
        return new ResponseDto(HttpStatus.OK.value(), "Total quantity allow " +
                "material of list internal supply contract", materialDto1List);
    }
    @Transactional
    public ResponseDto getBranchShopExistInInternalSC(){
        List<BranchShopDto> branchShopDtos = new ArrayList<>();
        //lay danh sach nguyen lieu len
        List<BranchShop> branchShopsToFind = branchShopRepository.findAllByEnable(true);
        branchShopsToFind.forEach(branchShop -> {//voi moi chi nhanh, tim xem no co trong hdcc co statu=1 va
            //date create nho hon today
            List<InternalSC> internalSCList = this.internalSCRepository
                    .findByBranchShopIdAndStatusAndDateCreateLessThanAndEnable(
                            branchShop.getId(), 1, LocalDate.now(),true);
            if(!internalSCList.isEmpty()){
                BranchShopDto branchShopDto = new BranchShopDto();
                branchShopDto.setId(branchShop.getId());
                branchShopDto.setName(branchShop.getName());
                branchShopDto.setAddress(branchShop.getAddress());
                branchShopDtos.add(branchShopDto);
            }

        });

        return new ResponseDto(HttpStatus.OK.value(), "All branch shop existed in internal supply contract",
                branchShopDtos);
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
