package coffeesystem.service.inventory;

import coffeesystem.dto.*;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.InventoryId;
import coffeesystem.repository.*;
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
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialPriceRepository materialPriceRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    @Autowired
    InventoryControlRepository inventoryControlRepository;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ResponseDto createInventory(InventoryRequestDto inventoryRequestDto){

            float backlogFirstDate = 0;
            if(!inventoryRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(inventoryRequestDto.getMaterialId(),
                    inventoryRequestDto.getBranchShopId(),true).isEmpty()){
                Inventory inventoryOld = inventoryRepository.findByMaxFirstDateByIdMaterialAndIdBranchShopAndEnable(
                        inventoryRequestDto.getMaterialId(), inventoryRequestDto.getBranchShopId(), true)
                        .orElseThrow(()-> new NotFoundException("Inventory not found"));

                //neu nhu ton kho cua nguyen lieu cua chi nhanh khong bi dong ky
                //thi cap nhat inventoryOld
                //con neu da bi dong ky thi tao moi luon
                if(inventoryOld.getStatus().equals("active")){
                    //neu trong 1 ngay tao ton kho cho nguyen lieu cua chi nhanh them lan nua
                    //thi ngay cuoi ky se la ngay dau ky luon (khong lay ngay dau ky tiep theo -1)
                    if(inventoryOld.getInventoryId().getFirstDate() == inventoryRequestDto.getFirstDate()){
                        inventoryOld.setLastDate(inventoryOld.getInventoryId().getFirstDate());
                    }else {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(Date.valueOf(inventoryRequestDto.getFirstDate()));
                        calendar.add(calendar.DATE, -1);
                        System.out.println(calendar);
                        Instant instant = calendar.toInstant();
                        LocalDate lastDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
                        //cap nhat last date cua inventory old
                        inventoryOld.setLastDate(lastDate);
                    }

                    //set trang thai completed cho inventory old
                    inventoryOld.setStatus("completed");
                    //set so luong con lai cua bang kiem kho qua bang ton
                    InventoryControl inventoryControl = inventoryControlRepository
                            .findByIdMaterialAndIdBranchShopAndStatusActiveAndEnable(inventoryRequestDto.getMaterialId(),
                                    inventoryRequestDto.getBranchShopId(), true)
                            .orElseThrow(()-> new NotFoundException("Inventory control not found"));
                    inventoryOld.setBacklogLastDate(inventoryControl.getRemainingAmount());
                    float quantitySold = inventoryOld.getBacklogFirstDate() + inventoryOld.getImportPeriod() -
                            inventoryOld.getBacklogLastDate();
                    //set quantitySold cua inventory old
                    inventoryOld.setQuantitySold(quantitySold);
                    //set gia ban cua nguyen lieu=gia von * so luong ban
                    inventoryOld.setPriceSold(inventoryOld.getQuantitySold() * inventoryOld.getCostPrice());
                    inventoryRepository.save(inventoryOld);
                    backlogFirstDate = inventoryOld.getBacklogLastDate();
                }

            }
            Inventory inventory = mapperObject.InventoryDtoToEntity2(inventoryRequestDto);
            Material material = materialRepository.findByIdAndEnable(inventoryRequestDto.getMaterialId(), true)
                    .orElseThrow(()-> new NotFoundException("Material not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(inventoryRequestDto.getBranchShopId(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop not found"));
            InventoryId inventoryId = new InventoryId();
            inventoryId.setIdMaterial(inventoryRequestDto.getMaterialId());
            inventoryId.setIdBranchShop(inventoryRequestDto.getBranchShopId());
            inventoryId.setFirstDate(inventoryRequestDto.getFirstDate());
            Integer idOld = inventoryRepository.findMaxId();
            if(idOld == null){
                idOld = 0;
            }
            inventoryId.setId(idOld + 1);
            inventory.setInventoryId(inventoryId);
            inventory.setMaterial(material);
            inventory.setBranchShop(branchShop);
            inventory.setBacklogFirstDate(backlogFirstDate);
            //lay gia von cua nguyen lieu
            MaterialPrice materialPrice = materialPriceRepository
                    .findByMaterialPriceIdIdMaterialAndEnable(inventoryRequestDto.getMaterialId(), true)
                    .orElseThrow(()-> new NotFoundException("Material is not have price"));
            inventory.setCostPrice(materialPrice.getCostPrice());
            inventoryRepository.save(inventory);

            return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);

    }
    public ResponseDto editInventory(InventoryRequestDto inventoryRequestDto){
        //status = active moi sua duoc
        Inventory inventory = inventoryRepository.
                findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndStatusAndEnable(
                        inventoryRequestDto.getMaterialId(),
                        inventoryRequestDto.getBranchShopId(), inventoryRequestDto.getFirstDate(),
                        "active", true).
                orElseThrow(()-> new NotFoundException("Inventory not found"));
        inventory.setImportPeriod(inventoryRequestDto.getImportPeriod());
//        inventory.setBacklogLastDate(inventory.getBacklogLastDate());
        inventoryRepository.save(inventory);

        return new ResponseDto(HttpStatus.OK.value(), "Edit successful", null);
    }
    public ResponseDto endOfPeriod(InventoryRequestDto inventoryRequestDto){
        Inventory inventoryOld = inventoryRepository.findByMaxFirstDateByIdMaterialAndIdBranchShopAndEnable(
                inventoryRequestDto.getMaterialId(), inventoryRequestDto.getBranchShopId(), true)
                .orElseThrow(()-> new NotFoundException("Inventory not found"));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf(inventoryRequestDto.getFirstDate()));
//        calendar.add(calendar.DATE, -1);
        System.out.println(calendar);
        Instant instant = calendar.toInstant();
        LocalDate lastDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        //cap nhat last date cua inventory old
        inventoryOld.setLastDate(lastDate);
        //set trang thai completed cho inventory old
        inventoryOld.setStatus("completed");
        //set so luong con lai cua bang kiem kho qua bang ton
        InventoryControl inventoryControl = inventoryControlRepository
                .findByIdMaterialAndIdBranchShopAndStatusActiveAndEnable(inventoryRequestDto.getMaterialId(),
                        inventoryRequestDto.getBranchShopId(), true)
                .orElseThrow(()-> new NotFoundException("Inventory control not found"));
        inventoryOld.setBacklogLastDate(inventoryControl.getRemainingAmount());
        float quantitySold = inventoryOld.getBacklogFirstDate() + inventoryOld.getImportPeriod() -
                inventoryOld.getBacklogLastDate();
        //set quantitySold cua inventory old
        inventoryOld.setQuantitySold(quantitySold);
        //set gia ban cua nguyen lieu=gia von * so luong ban
        inventoryOld.setPriceSold(inventoryOld.getQuantitySold() * inventoryOld.getCostPrice());
        inventoryRepository.save(inventoryOld);
        return new ResponseDto(HttpStatus.OK.value(), "End of period", null);
    }
    @Transactional
    public ResponseDto getAllInventory(){
        List<Inventory> inventoryList = this.inventoryRepository.findAllByEnableOrderByStatusAscInventoryIdDesc(true);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        inventoryList.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setCostPrice(element.getCostPrice());
            inventoryResponseDto.setPriceSold(element.getPriceSold());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDtos);
    }
    @Transactional
    public PagingResponseDto getAllInventoryPaging(int page, int size, String sort, String sortColumn){

        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        Page<Inventory> inventoryPage = inventoryRepository.findAllByEnable(true, pageable);

        inventoryPage.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);});
        Page<InventoryResponseDto> inventoryResponseDtoPage = new PageImpl<>(inventoryResponseDtos, pageable,
                inventoryPage.getTotalElements());
        return new PagingResponseDto<>(
                inventoryResponseDtoPage.getContent(), inventoryResponseDtoPage.getTotalElements(),
                inventoryResponseDtoPage.getTotalPages(), inventoryResponseDtoPage.getPageable());

    }
    @Transactional
    public PagingResponseDto getAllByBranchShopIdPaging(int page, int size, String sort,
                                                        String sortColumn, Integer branchShopId){

        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        Page<Inventory> inventoryPage = inventoryRepository
                .findByInventoryIdIdBranchShopAndEnableOrderByInventoryIdDesc(branchShopId,
                        true, pageable);

        inventoryPage.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);});
        Page<InventoryResponseDto> inventoryResponseDtoPage = new PageImpl<>(inventoryResponseDtos, pageable,
                inventoryPage.getTotalElements());
        return new PagingResponseDto<>(
                inventoryResponseDtoPage.getContent(), inventoryResponseDtoPage.getTotalElements(),
                inventoryResponseDtoPage.getTotalPages(), inventoryResponseDtoPage.getPageable());

    }
    @Transactional
    public ResponseDto getAllByBranchShopId(Integer branchShopId){


        List<Inventory> inventoryList = inventoryRepository
                .findByInventoryIdIdBranchShopAndEnableOrderByStatusAscInventoryIdDesc(branchShopId, true);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        inventoryList.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);});
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDtos);

    }
    @Transactional
    public PagingResponseDto getAllByBranchShopIdAndStatusPaging(int page, int size, String sort,
                                                        String sortColumn, Integer branchShopId, String status){

        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        Page<Inventory> inventoryPage = inventoryRepository.
                findByInventoryIdIdBranchShopAndStatusAndEnable(branchShopId, status,
                        true,pageable);

        inventoryPage.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);});
        Page<InventoryResponseDto> inventoryResponseDtoPage = new PageImpl<>(inventoryResponseDtos, pageable,
                inventoryPage.getTotalElements());
        return new PagingResponseDto<>(
                inventoryResponseDtoPage.getContent(), inventoryResponseDtoPage.getTotalElements(),
                inventoryResponseDtoPage.getTotalPages(), inventoryResponseDtoPage.getPageable());

    }
    @Transactional
    public ResponseDto getByIdMaterialAndIdBranchShop(Integer materialId, Integer branchShopId){
        List<Inventory> inventoryList = this.inventoryRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
                materialId, branchShopId, true);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        inventoryList.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDtos);
    }
    @Transactional
    public ResponseDto getByIdMaterialAndIdBranchShopStatusActive(Integer materialId, Integer branchShopId){
        List<Inventory> inventoryList = this.inventoryRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndStatusAndEnable(
                materialId, branchShopId, "active",true);
        List<InventoryResponseDto> inventoryResponseDtos = new ArrayList<>();
        inventoryList.forEach(element->{
            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(element);
            Material material = materialRepository.findByIdAndEnable(element.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(element.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
            inventoryResponseDto.setId(element.getInventoryId().getId());
            inventoryResponseDto.setMaterialId(element.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(element.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(element.getStatus());
            inventoryResponseDto.setFirstDate(element.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(element.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(element.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            inventoryResponseDtos.add(inventoryResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDtos);
    }
    @Transactional
    public ResponseDto getByIdMaterialAndIdBranchShopAndFirstDate(
            Integer materialId, Integer branchShopId, String firstDate){
                LocalDate newFirstDate = LocalDate.parse(firstDate, dtf);
        Inventory inventory = this.inventoryRepository.findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndInventoryIdFirstDateAndEnable(
                materialId, branchShopId, newFirstDate, true).
                orElseThrow(()-> new NotFoundException("Inventory not found"));

            InventoryResponseDto inventoryResponseDto = mapperObject.InventoryEntityToDto(inventory);
            Material material = materialRepository.findByIdAndEnable(inventory.getInventoryId().getIdMaterial(), true)
                    .orElseThrow(()-> new NotFoundException("Material id not found"));
            BranchShop branchShop = branchShopRepository.findByIdAndEnable(inventory.getInventoryId().getIdBranchShop(), true)
                    .orElseThrow(()-> new NotFoundException("Branch shop id not found"));
//            minMaxInventoryResponseDto.setId(element.getMinMaxInventoryId().getId());
            inventoryResponseDto.setMaterialId(inventory.getInventoryId().getIdMaterial());
            inventoryResponseDto.setBranchShopId(inventory.getInventoryId().getIdBranchShop());
            inventoryResponseDto.setBranchShopName(branchShop.getName());
            inventoryResponseDto.setMaterialName(material.getName());
            inventoryResponseDto.setUnitName(material.getUnit().getName());
            inventoryResponseDto.setStatus(inventory.getStatus());
            inventoryResponseDto.setFirstDate(inventory.getInventoryId().getFirstDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if(inventory.getLastDate() == null){
                inventoryResponseDto.setLastDate("null");
            }
            else {
                inventoryResponseDto.setLastDate(inventory.getLastDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

        return new ResponseDto(HttpStatus.OK.value(), "All inventory", inventoryResponseDto);
    }
    @Transactional
    public ResponseDto getMaterialExistInInventoryByIdBranchShop(Integer branchShopId){
        List<IdNameDto> idNameDtos = new ArrayList<>();
        //lay danh sach nguyen lieu len
        List<Material> materialList = materialRepository.findAllByEnable(true);
        materialList.forEach(material -> {//voi moi nguyen lieu, tim xem no co ton kho khong
            List<Inventory> inventoryList = this.inventoryRepository
                    .findByInventoryIdIdMaterialAndInventoryIdIdBranchShopAndEnable(
                            material.getId(), branchShopId,true);
            if(!inventoryList.isEmpty()){
                IdNameDto idNameDto = new IdNameDto();
                idNameDto.setId(material.getId());
                idNameDto.setName(material.getName());
                idNameDtos.add(idNameDto);
            }

        });

        return new ResponseDto(HttpStatus.OK.value(), "All material existed in inventory", idNameDtos);
    }

    public ResponseDto deleteInventory(Integer id){
        Inventory inventory = inventoryRepository.
                findByInventoryIdIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Inventory not found"));
        inventory.setEnable(false);
        inventoryRepository.save(inventory);
        return new ResponseDto(HttpStatus.OK.value(), "Delete inventory successful", null);
    }
}
