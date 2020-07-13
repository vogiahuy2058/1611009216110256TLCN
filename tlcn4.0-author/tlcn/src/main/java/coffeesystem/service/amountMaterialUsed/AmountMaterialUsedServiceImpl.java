package coffeesystem.service.amountMaterialUsed;

import coffeesystem.dto.AmountMaterialUsedResponseDto;
import coffeesystem.dto.IdNameDto;
import coffeesystem.dto.MaterialDto2;
import coffeesystem.dto.ResponseDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.*;
import coffeesystem.model.embedding.AmountMaterialUsedId;
import coffeesystem.model.embedding.InventoryId;
import coffeesystem.repository.AmountMaterialUsedRepository;
import coffeesystem.repository.BranchShopRepository;
import coffeesystem.repository.MaterialPriceRepository;
import coffeesystem.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
public class AmountMaterialUsedServiceImpl implements AmountMaterialUsedService{
    @Autowired
    AmountMaterialUsedRepository amuRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    BranchShopRepository branchShopRepository;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public ResponseDto createAmountMaterialUsed(Integer id, Integer idBranchShop,
                                                           Integer idMaterial,
                                                           String checkDate){
        LocalDate newCheckDate = LocalDate.parse(checkDate, dtf);
        if(!amuRepository.findByMaterialIdAndAmountMaterialUsedIdIdBranchShop(idMaterial,
                idBranchShop).isEmpty()){
            AmountMaterialUsed amountMaterialUsedOld = amuRepository.findByMaxCheckDateByIdMaterialAndIdBranchShop(
                   idMaterial, idBranchShop)
                    .orElseThrow(()-> new NotFoundException("Amount material used not found"));
                //set trang thai completed cho amount material used old
            amountMaterialUsedOld.setStatus("completed");
            amuRepository.save(amountMaterialUsedOld);
            }

        AmountMaterialUsed amountMaterialUsed = new AmountMaterialUsed();
        Material material = materialRepository.findByIdAndEnable(idMaterial, true)
                .orElseThrow(()-> new NotFoundException("Material not found"));
        BranchShop branchShop = branchShopRepository.findByIdAndEnable(idBranchShop, true)
                .orElseThrow(()-> new NotFoundException("Branch shop not found"));
        AmountMaterialUsedId amountMaterialUsedId = new AmountMaterialUsedId();
        amountMaterialUsedId.setIdMaterial(idMaterial);
        amountMaterialUsedId.setIdBranchShop(idBranchShop);
        amountMaterialUsedId.setCheckDate(newCheckDate);
//        Integer idOld = amuRepository.findMaxId();
//        if(idOld == null){
//            idOld = 0;
//        }
        amountMaterialUsedId.setId(id);
        amountMaterialUsed.setAmountMaterialUsedId(amountMaterialUsedId);
        amountMaterialUsed.setMaterial(material);
        amountMaterialUsed.setBranchShop(branchShop);
        amountMaterialUsed.setTotalMinAmount(0);
        amountMaterialUsed.setTotalMaxAmount(0);
        amountMaterialUsed.setTotalAverageAmount(0);
        amuRepository.save(amountMaterialUsed);
        return new ResponseDto(HttpStatus.OK.value(), "Create successful", null);
    }
    public void updateAmountMaterialUsed(Integer idBranchShop,
                                                    Integer idMaterial,
                                                    float newMinAmount,
                                                    float newMaxAmount,
                                         float newAverageAmount){
        AmountMaterialUsed amountMaterialUsed = amuRepository
                .findByMaterialIdAndAmountMaterialUsedIdIdBranchShopAndStatus(
                idMaterial, idBranchShop, "active").get();
        float oldTotalMinAmount = amountMaterialUsed.getTotalMinAmount();
        float oldTotalMaxAmount = amountMaterialUsed.getTotalMaxAmount();
        float oldTotalAverageAmount = amountMaterialUsed.getTotalAverageAmount();
        amountMaterialUsed.setTotalMinAmount(oldTotalMinAmount + newMinAmount);
        amountMaterialUsed.setTotalMaxAmount(oldTotalMaxAmount + newMaxAmount);
        amountMaterialUsed.setTotalAverageAmount(oldTotalAverageAmount + newAverageAmount);
        amuRepository.save(amountMaterialUsed);
    }
    public ResponseDto getAmountMaterialUsedStatusActive(Integer idBranchShop,
                                                         Integer idMaterial){

        if(!amuRepository
                .findByMaterialIdAndAmountMaterialUsedIdIdBranchShopAndStatus(
                        idMaterial, idBranchShop, "active").isPresent()){
            return new ResponseDto(HttpStatus.OK.value(), "Amount of material used", null);
        }
        AmountMaterialUsed amountMaterialUsed = amuRepository
                .findByMaterialIdAndAmountMaterialUsedIdIdBranchShopAndStatus(
                        idMaterial, idBranchShop, "active").get();
        AmountMaterialUsedResponseDto responseDto = new AmountMaterialUsedResponseDto();
        responseDto.setId(amountMaterialUsed.getAmountMaterialUsedId().getId());
        responseDto.setIdMaterial(amountMaterialUsed.getMaterial().getId());
        responseDto.setIdBranchShop(amountMaterialUsed.getBranchShop().getId());
        responseDto.setCheckDate(amountMaterialUsed.getAmountMaterialUsedId().getCheckDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        responseDto.setTotalMinAmount(amountMaterialUsed.getTotalMinAmount());
        responseDto.setTotalMaxAmount(amountMaterialUsed.getTotalMaxAmount());
        responseDto.setTotalAverageAmount(amountMaterialUsed.getTotalAverageAmount());
        return new ResponseDto(HttpStatus.OK.value(), "Amount of material used", responseDto);
    }
    public ResponseDto getRecommendInventoryControl(Integer idBranchShop){
        List<Material> materialList = materialRepository.findAllByEnable(true);
        List<MaterialDto2> materialDto2List = new ArrayList<>();
        materialList.forEach(material -> {
            if(amuRepository.findByMaterialIdAndAmountMaterialUsedIdIdBranchShopAndStatus(
                    material.getId(), idBranchShop, "active").isPresent()){
                AmountMaterialUsed amountMaterialUsed = amuRepository.findByMaterialIdAndAmountMaterialUsedIdIdBranchShopAndStatus(
                        material.getId(), idBranchShop, "active").get();
                if(amountMaterialUsed.getTotalAverageAmount() >= material.getInventoryQuota()){
                    MaterialDto2 materialDto2 = new MaterialDto2();
                    materialDto2.setId(material.getId());
                    materialDto2.setName(material.getName());
                    materialDto2.setMaterialType(material.getMaterialType().getName());
                    materialDto2.setUnitName(material.getUnit().getName());
                    materialDto2.setRemainingAmount(null);
                    materialDto2List.add(materialDto2);

                }
            }

        });


        return new ResponseDto(HttpStatus.OK.value(), "List recommend inventory control", materialDto2List);
    }
    public ResponseDto getMaxIdAMU(){
        Integer idOld = amuRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        return new ResponseDto(HttpStatus.OK.value(), "Max id", idOld);
    }
}
