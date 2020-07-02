package coffeesystem.service.inventoryControl;

import coffeesystem.dto.*;
import coffeesystem.exception.ExistException;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.InventoryId;
import coffeesystem.repository.*;
import coffeesystem.service.customer.CustomerService;
import coffeesystem.service.inventory.InventoryService;
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
    @Autowired
    InventoryService inventoryService;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ResponseDto createInventoryControl(InventoryControlRequestDto requestDto){
        if(!inventoryControlRepository.
                findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
                        requestDto.getMaterialId(),
                        requestDto.getBranchShopId(), true).isEmpty()){
            throw new ExistException("Inventory control was existed");
//            return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
        }
        else {
//            if(!inventoryControlRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(requestDto.getMaterialId(),
//                    requestDto.getBranchShopId(),true).isEmpty()){
//                //status active nghia la nguyen lieu hien co so luong bao nhieu trong kho
//                //chi co 1 nguyen lieu cua 1 chi nhanh co status = active
//                //con lai cac dong khac status=completed
//                List<InventoryControl> inventoryControlOld = inventoryControlRepository
//                        .findAllByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndStatusAndEnable(
//                                requestDto.getMaterialId(), requestDto.getBranchShopId(), "active", true);
//                inventoryControlOld.forEach(element->{
//                    //set trang thai completed cho inventoryControl old
//                    element.setStatus("completed");
//                    inventoryControlRepository.save(element);
//                });
//
//            }
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

    }
    public ResponseDto updateInventoryControl(InventoryControlRequestDto requestDto){

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

            // neu remaining amount == 0 thi dong ky
            if(requestDto.getRemainingAmount() == 0){
                InventoryRequestDto inventoryRequestDto = new InventoryRequestDto();
                inventoryRequestDto.setFirstDate(requestDto.getCheckDate());
                inventoryRequestDto.setMaterialId(requestDto.getMaterialId());
                inventoryRequestDto.setBranchShopId(requestDto.getBranchShopId());
                inventoryRequestDto.setId(0);
                inventoryRequestDto.setImportPeriod(0);
                inventoryService.endOfPeriod(inventoryRequestDto);
            }

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
            inventoryControlResponseDto.setStatus(element.getStatus());
            inventoryControlResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDto.setCheckDate(element.getCheckDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDtos.add(inventoryControlResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryControlResponseDtos);
    }
    @Transactional
    public ResponseDto getAllInventoryControlByIdBranchShopStatusActive(Integer idBranchShop){
        List<InventoryControl> inventoryControlList = this.inventoryControlRepository
                .findAllByInventoryIdIdBranchShopAndStatusAndEnableOrderByMaterialDesc(idBranchShop, "active", true);
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
            inventoryControlResponseDto.setStatus(element.getStatus());
            inventoryControlResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDto.setCheckDate(element.getCheckDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDtos.add(inventoryControlResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryControlResponseDtos);
    }
    @Transactional
    public ResponseDto getInventoryControlByIdMaterialIdBranchShopStatusActive(Integer idMaterial,Integer idBranchShop){
        List<InventoryControl> inventoryControlList = this.inventoryControlRepository
                .findAllByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndStatusAndEnable(
                        idMaterial, idBranchShop, "active", true);
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
            inventoryControlResponseDto.setStatus(element.getStatus());
            inventoryControlResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDto.setCheckDate(element.getCheckDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDtos.add(inventoryControlResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryControlResponseDtos);
    }

    public ResponseDto deleteInventoryControl(Integer idMaterial, Integer idBranchShop, String firstDate){
        LocalDate newFirstDate = LocalDate.parse(firstDate, dtf);
        InventoryControl inventoryControl = this.inventoryControlRepository
                .findByIdMaterialAndIdBranchShopAndFirstDateAndStatusActiveAndEnable(idMaterial,idBranchShop,
                        newFirstDate, true)
                .orElseThrow(()-> new NotFoundException("Inventory control not found"));
        inventoryControl.setEnable(false);
        inventoryControlRepository.save(inventoryControl);

        return new ResponseDto(HttpStatus.OK.value(), "Delete successful", null);
    }
    @Transactional
    public ResponseDto getAllInventoryControl(){
        List<InventoryControl> inventoryControlList = this.inventoryControlRepository.findAllByEnableOrderByMaterialDesc(true);
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
            inventoryControlResponseDto.setStatus(element.getStatus());
            inventoryControlResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDto.setCheckDate(element.getCheckDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            inventoryControlResponseDtos.add(inventoryControlResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryControlResponseDtos);
    }
    @Transactional
    public ResponseDto getMaterialExistInInventoryControlByIdBranchShop(Integer branchShopId){
        List<MaterialForMinMax> materialForMinMaxes = new ArrayList<>();
        //lay danh sach nguyen lieu len
        List<Material> materialList = materialRepository.findAllByEnable(true);
        materialList.forEach(material -> {//voi moi nguyen lieu, tim xem no co ton kho khong
            List<InventoryControl> inventoryControlList = this.inventoryControlRepository
                    .findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
                            material.getId(), branchShopId,true);
            if(!inventoryControlList.isEmpty()){
                MaterialForMinMax materialForMinMax = new MaterialForMinMax();
                materialForMinMax.setId(material.getId());
                materialForMinMax.setName(material.getName());
                materialForMinMaxes.add(materialForMinMax);
            }

        });

        return new ResponseDto(HttpStatus.OK.value(), "All material existed in inventory", materialForMinMaxes);
    }


}
