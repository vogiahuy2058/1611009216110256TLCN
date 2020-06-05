package coffeesystem.controller;


import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplyContractDetailDto;
import coffeesystem.service.supplyContractDetail.SupplyContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/supply-contract-detail")
public class SupplyContractDetailController {
    @Autowired
    SupplyContractDetailService detailService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSupplyContractDetail(@RequestBody SupplyContractDetailDto supplyContractDetailDto){
        return ResponseEntity.ok(detailService.createSupplyContractDetail(supplyContractDetailDto));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editSupplyContractDetail(@RequestBody SupplyContractDetailDto supplyContractDetailDto){
        return ResponseEntity.ok(detailService.editSupplyContractDetail(supplyContractDetailDto));
    }
}
