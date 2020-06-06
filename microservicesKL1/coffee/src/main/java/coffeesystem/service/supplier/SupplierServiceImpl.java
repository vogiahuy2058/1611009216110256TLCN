package coffeesystem.service.supplier;

import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SupplierDto;
import coffeesystem.exception.NotFoundException;
import coffeesystem.model.Supplier;
import coffeesystem.model.SupplyContract;
import coffeesystem.repository.SupplierRepository;
import coffeesystem.repository.SupplyContractRepository;
import coffeesystem.service.supplyContract.SupplyContractService;
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

public class SupplierServiceImpl implements SupplierService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SupplyContractRepository supplyContractRepository;
    @Autowired
    SupplyContractService supplyContractService;
    public ResponseDto createSupplier(SupplierDto supplierDto){
        Supplier supplier = this.mapperObject.SupplierDtoToEntity(supplierDto);
        supplierRepository.save(supplier);
        return new ResponseDto(HttpStatus.OK.value(), "create supplier successful", null);
    }
    @Transactional
    public ResponseDto getAllSupplier(){
        List<Supplier> suppliers = supplierRepository.findAllByEnable(true);
        List<SupplierDto> supplierDtos = new ArrayList<>();
        suppliers.forEach(element->{
            SupplierDto supplierDto = mapperObject.SupplierEntityToDto(element);
            supplierDtos.add(supplierDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All supplier", supplierDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllSupplierPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<SupplierDto> supplierDtos = new ArrayList<>();
        Page<Supplier> supplierPage = supplierRepository.findAllByEnable(true, pageable);
        supplierPage.forEach(element->{
            SupplierDto supplierDto = mapperObject.SupplierEntityToDto(element);
            supplierDtos.add(supplierDto);});
        Page<SupplierDto> supplierDtoPage = new PageImpl<>(supplierDtos, pageable,
                supplierPage.getTotalElements() );
        return new PagingResponseDto<>(
                supplierDtoPage.getContent(), supplierDtoPage.getTotalElements(), supplierDtoPage.getTotalPages(),
                supplierDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getSupplierById(Integer id){
        Supplier supplier = supplierRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        SupplierDto supplierDto = mapperObject.SupplierEntityToDto(supplier);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", supplierDto);
    }
    public ResponseDto deleteSupplier(Integer id){
        Supplier supplier = supplierRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete supply contract when supplier was deleted
        List<SupplyContract> supplyContracts = supplyContractRepository.findBySupplierIdAndEnable(id, true);
        supplyContracts.forEach(element->{
            supplyContractService.deleteSupplyContract(element.getId());
        });
        supplier.setEnable(false);
        supplierRepository.save(supplier);
        return new ResponseDto(HttpStatus.OK.value(), "Delete supplier successful", null);
    }
    public ResponseDto editSupplier(SupplierDto supplierDto){
        Supplier supplier = supplierRepository.findByIdAndEnable(supplierDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //Not change total purchase
        supplier.setName(supplierDto.getName());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setTaxCode(supplierDto.getTaxCode());
        supplierRepository.save(supplier);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink type successful", null);
    }
}
