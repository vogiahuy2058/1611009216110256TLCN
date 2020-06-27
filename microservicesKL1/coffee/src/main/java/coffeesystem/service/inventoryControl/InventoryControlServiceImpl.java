package coffeesystem.service.inventoryControl;

import coffeesystem.dto.*;
import coffeesystem.exception.ExistException;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.InventoryId;
import coffeesystem.repository.*;
import coffeesystem.service.customer.CustomerService;
import coffeesystem.util.MapperObject;
import coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class InventoryControlServiceImpl implements InventoryControlService{
    @Autowired
    InventoryControlRepository inventoryControlRepository;
    @Autowired
    MapperObject mapperObject;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    public ResponseDto createInventoryControl(InventoryControlRequestDto requestDto){
            if(!inventoryControlRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(requestDto.getMaterialId(),
                    requestDto.getBranchShopId(),true).isEmpty()){
                //status active nghia la nguyen lieu hien co so luong bao nhieu trong kho
                //chi co 1 nguyen lieu cua 1 chi nhanh co status = active
                //con lai cac dong khac status=completed
                List<InventoryControl> inventoryControlOld = inventoryControlRepository
                        .findAllByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndStatusAndEnable(
                        requestDto.getMaterialId(), requestDto.getBranchShopId(), "active", true);
                inventoryControlOld.forEach(element->{
                    //set trang thai completed cho inventoryControl old
                    element.setStatus("completed");
                    inventoryControlRepository.save(element);
                });

            }
            InventoryControl inventoryControl = mapperObject.InventoryControlDtoToEntity(requestDto);
            Material material = materialRepository.findByIdAndEnable(requestDto.getMaterialId(), true)
                    .orElseThrow(()-> new NotFoundException("Material not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(requestDto.getBranchShopId(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop not found"));
            InventoryId inventoryId = new InventoryId();
            inventoryId.setIdMaterial(requestDto.getMaterialId());
            inventoryId.setIdBranchShop(requestDto.getBranchShopId());
            inventoryId.setFirstDate(requestDto.getFirstDate());
            Integer idOld = inventoryControlRepository.findMaxId();
            if(idOld == null){
                idOld = 0;
            }
            inventoryId.setId(idOld + 1);
            inventoryControl.setInventoryId(inventoryId);
            inventoryControl.setMaterial(material);
            inventoryControl.setBranchShop(branchShop);
            inventoryControlRepository.save(inventoryControl);

            return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
    }
//    public ResponseDto editInventory(InventoryRequestDto inventoryRequestDto){
//        //status = active moi sua duoc
//        Inventory inventory = inventoryRepository.
//                findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndStatusAndEnable(
//                        inventoryRequestDto.getMaterialId(),
//                        inventoryRequestDto.getBranchShopId(), inventoryRequestDto.getFirstDate(),
//                        "active", true).
//                orElseThrow(()-> new NotFoundException("Inventory not found"));
//        inventory.setImportPeriod(inventoryRequestDto.getImportPeriod());
//        inventory.setBacklogLastDate(inventory.getBacklogLastDate());
//        inventoryRepository.save(inventory);
//
//        return new ResponseDto(HttpStatus.OK.value(), "Edit successful", null);
//    }
    @Transactional
    public ResponseDto getAllInventoryControlStatusActive(){
        List<InventoryControl> inventoryControlList = this.inventoryControlRepository.findAllByStatusAndEnable("active", true);
        List<InventoryControlResponseDto> inventoryControlResponseDtos = new ArrayList<>();
        inventoryControlList.forEach(element->{
            InventoryControlResponseDto inventoryControlResponseDto = mapperObject.InventoryControlEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryControlResponseDto.setId(element.getInventoryId().getId());
            inventoryControlResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryControlResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryControlResponseDto.setBranchShopName(branchShop.getName());
            inventoryControlResponseDto.setMaterialName(material.getName());
            inventoryControlResponseDto.setUnitName(material.getUnit().getName());
            inventoryControlResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDto.setCheckDate(element.getCheckDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDtos.add(inventoryControlResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryControlResponseDtos);
    }
//    @Transactional
//    public PagingResponseDto getAllInventoryPaging(int page, int size, String sort, String sortColumn){
//
//        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
//        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
//        Page<Inventory> inventoryPage = inventoryRepository.findAllByEnable(true, pageable);
//
//        inventoryPage.forEach(element->{
//            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
//            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
//                    .orElseThrow(()-> new NotFoundException("Material id not found"));
//            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
//                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
//            inventoryResponseDto.setId(element.getInventoryId().getId());
//            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
//            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
//            inventoryResponseDto.setBranchShopName(branchShop.getName());
//            inventoryResponseDto.setMaterialName(material.getName());
//            inventoryResponseDto.setUnitName(material.getUnit().getName());
//            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
//                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            if(element.getLastDate() == null){
//                inventoryResponseDto.setLastDate("null");
//            }
//            else {
//                inventoryResponseDto.setLastDate(element.getLastDate()
//                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
//            inventoryResponseDtos.add(inventoryResponseDto);});
//        Page<InventoryResponseDto> inventoryResponseDtoPage = new PageImpl<>(inventoryResponseDtos, pageable,
//                inventoryPage.getTotalElements());
//        return new PagingResponseDto<>(
//                inventoryResponseDtoPage.getContent(), inventoryResponseDtoPage.getTotalElements(),
//                inventoryResponseDtoPage.getTotalPages(), inventoryResponseDtoPage.getPageable());
//
//    }
//    @Transactional
//    public PagingResponseDto getAllByBranchShopIdPaging(int page, int size, String sort,
//                                                        String sortColumn, Integer branchShopId){
//
//        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
//        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
//        Page<Inventory> inventoryPage = inventoryRepository.findByInventoryIdIdBranchShopAndEnable(branchShopId, true, pageable);
//
//        inventoryPage.forEach(element->{
//            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
//            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
//                    .orElseThrow(()-> new NotFoundException("Material id not found"));
//            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
//                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
//            inventoryResponseDto.setId(element.getInventoryId().getId());
//            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
//            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
//            inventoryResponseDto.setBranchShopName(branchShop.getName());
//            inventoryResponseDto.setMaterialName(material.getName());
//            inventoryResponseDto.setUnitName(material.getUnit().getName());
//            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
//                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            if(element.getLastDate() == null){
//                inventoryResponseDto.setLastDate("null");
//            }
//            else {
//                inventoryResponseDto.setLastDate(element.getLastDate()
//                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
//            inventoryResponseDtos.add(inventoryResponseDto);});
//        Page<InventoryResponseDto> inventoryResponseDtoPage = new PageImpl<>(inventoryResponseDtos, pageable,
//                inventoryPage.getTotalElements());
//        return new PagingResponseDto<>(
//                inventoryResponseDtoPage.getContent(), inventoryResponseDtoPage.getTotalElements(),
//                inventoryResponseDtoPage.getTotalPages(), inventoryResponseDtoPage.getPageable());
//
//    }
//    @Transactional
//    public ResponseDto getByIdMaterialAndIdBranchShop(Integer materialId, Integer branchShopId){
//        List<Inventory> inventoryList = this.inventoryRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
//                materialId, branchShopId, true);
//        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
//        inventoryList.forEach(element->{
//            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
//            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
//                    .orElseThrow(()-> new NotFoundException("Material id not found"));
//            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
//                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
//            inventoryResponseDto.setId(element.getInventoryId().getId());
//            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
//            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
//            inventoryResponseDto.setBranchShopName(branchShop.getName());
//            inventoryResponseDto.setMaterialName(material.getName());
//            inventoryResponseDto.setUnitName(material.getUnit().getName());
//            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
//                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            if(element.getLastDate() == null){
//                inventoryResponseDto.setLastDate("null");
//            }
//            else {
//                inventoryResponseDto.setLastDate(element.getLastDate()
//                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
//            inventoryResponseDtos.add(inventoryResponseDto);
//        });
//        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDtos);
//    }
//    @Transactional
//    public ResponseDto getByIdMaterialAndIdBranchShopAndFirstDate(
//            Integer materialId, Integer branchShopId, String firstDate){
//        LocalDate newFirstDate = LocalDate.parse(firstDate, dtf);
//        Inventory inventory = this.inventoryRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndEnable(
//                materialId, branchShopId, newFirstDate, true).
//                orElseThrow(()-> new NotFoundException("Inventory not found"));
//
//        InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(inventory);
//        Material material = materialRepository.findByIdAndEnable(inventory.getInventoryId().getIdMaterial(), true)
//                .orElseThrow(()-> new NotFoundException("Material id not found"));
//        BranchShop branchShop = branchShopRepository.findByIdAndEnable(inventory.getInventoryId().getIdBranchShop(), true)
//                .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
////            minMaxInventoryResponseDto.setId(element.getMinMaxInventoryId().getId());
//        inventoryResponseDto.setMaterialId(inventory.getInventoryId().getIdMaterial());
//        inventoryResponseDto.setBranchShopId(inventory.getInventoryId().getIdBranchShop());
//        inventoryResponseDto.setBranchShopName(branchShop.getName());
//        inventoryResponseDto.setMaterialName(material.getName());
//        inventoryResponseDto.setUnitName(material.getUnit().getName());
//        inventoryResponseDto.setFirstDate(inventory.getInventoryId().getFirstDate()
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        if(inventory.getLastDate() == null){
//            inventoryResponseDto.setLastDate("null");
//        }
//        else {
//            inventoryResponseDto.setLastDate(inventory.getLastDate()
//                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        }
//
//        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDto);
//    }
//    public ResponseDto deleteInventory(Integer id){
//        Inventory inventory = inventoryRepository.
//                findByInventoryIdIdAndEnable(id, true)
//                .orElseThrow(()-> new NotFoundException("Inventory not found"));
//        inventory.setEnable(false);
//        inventoryRepository.save(inventory);
//        return new ResponseDto(HttpStatus.OK.value(), "Delete inventory successful", null);
//    }
}
