package coffeesystem.controller;

import coffeesystem.dto.BranchShopDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.service.amountMaterialUsed.AmountMaterialUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/amu")
public class AmountMaterialUsedController {
    @Autowired
    AmountMaterialUsedService amountMaterialUsedService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAmountMaterialUsed(@RequestParam Integer id,
                                                                @RequestParam Integer idBranchShop,
                                                                @RequestParam Integer idMaterial,
                                                                @RequestParam String checkDate){
        return ResponseEntity.ok(amountMaterialUsedService.createAmountMaterialUsed(id,
                idBranchShop, idMaterial, checkDate));
    }
    @GetMapping("/get-max-id")
    public ResponseEntity<ResponseDto> getMaxInvoiceId(){

        return ResponseEntity.ok(amountMaterialUsedService.getMaxIdAMU());
    }
    @GetMapping("/get-amount-of-material-used-status-active")
    public ResponseEntity<ResponseDto> getAmountMaterialUsedStatusActive(@RequestParam Integer idBranchShop,
                                                                @RequestParam Integer idMaterial){
        return ResponseEntity.ok(amountMaterialUsedService.getAmountMaterialUsedStatusActive(
                idBranchShop, idMaterial));
    }
}
