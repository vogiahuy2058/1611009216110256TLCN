package coffeesystem.service.account;


import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.dto.SignUpDto;
import coffeesystem.dto.UpgradeAccountDto;
import org.springframework.security.access.prepost.PreAuthorize;

public interface AccountService {
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto createAccount(SignUpDto signUpDto);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto createAccountChangeRole(UpgradeAccountDto upgradeAccountDto);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto deleteAccount(Integer id);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getAccount(String username);
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseDto getAllAccount();
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    PagingResponseDto getAllAccountPaging(int page, int size, String sort, String sortColumn);
}
