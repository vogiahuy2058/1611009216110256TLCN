package coffeesystem.service.materialPrice;


import coffeesystem.dto.MaterialPriceRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface MaterialPriceService {

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto createPriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto changePriceOfMaterial(MaterialPriceRequestDto materialPriceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getPriceOfMaterial(Integer materialId);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGE_ALL_BRANCH', 'ROLE_BRANCH_MANAGER')")
    ResponseDto getAllPriceOfMaterial();
    PagingResponseDto getAllMaterialPricePaging(int page, int size, String sort, String sortColumn);
}
