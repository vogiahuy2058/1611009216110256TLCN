package com.springboot.angular.coffeesystem.service.account;

import com.springboot.angular.coffeesystem.dto.*;
import com.springboot.angular.coffeesystem.exception.ExistException;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Account;
import com.springboot.angular.coffeesystem.model.Employee;
import com.springboot.angular.coffeesystem.model.Role;
import com.springboot.angular.coffeesystem.model.RoleName;
import com.springboot.angular.coffeesystem.repository.AccountRepository;
import com.springboot.angular.coffeesystem.repository.EmployeeRepository;
import com.springboot.angular.coffeesystem.repository.RoleRepository;
import com.springboot.angular.coffeesystem.service.employee.EmployeeService;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MapperObject mapperObject;
    @Autowired
    EmployeeService employeeService;

    @Transactional
    public ResponseDto createAccount(SignUpDto signUpDto){
        Account account = new Account(signUpDto.getUsername(), encoder.encode(signUpDto.getPassword()), signUpDto.getEmail());
        Set<String> strRole = signUpDto.getRole();
        Set<Role> roleSet = new HashSet<>();
        strRole.forEach(r->{
            switch (r){
                case "admin":
                    Role adminRole = roleRepository.findByNameAndEnable(RoleName.ROLE_ADMIN, true)
                            .orElseThrow(() -> new NotFoundException("Fail! -> Cause: Role not found."));
                    roleSet.add(adminRole);
                    break;
                case "branch_manager":
                    Role bmRole = roleRepository.findByNameAndEnable(RoleName.ROLE_BRANCH_MANAGER, true)
                            .orElseThrow(() -> new NotFoundException("Fail! -> Cause: Role not found."));
                    roleSet.add(bmRole);

                    break;
                case "hr":
                    Role hrRole = roleRepository.findByNameAndEnable(RoleName.ROLE_HR, true)
                            .orElseThrow(() -> new NotFoundException("Fail! -> Cause: Role not found."));
                    roleSet.add(hrRole);

                    break;
                case "cashier":
                    Role cashierRole = roleRepository.findByNameAndEnable(RoleName.ROLE_CASHIER, true)
                            .orElseThrow(() -> new NotFoundException("Fail! -> Cause: Role not found."));
                    roleSet.add(cashierRole);

                    break;
                case "accountant":
                    Role accountantRole = roleRepository.findByNameAndEnable(RoleName.ROLE_ACCOUNTANT, true)
                            .orElseThrow(() -> new NotFoundException("Fail! -> Cause: Role not found."));
                    roleSet.add(accountantRole);

                    break;
                case "chef":
                    Role chefRole = roleRepository.findByNameAndEnable(RoleName.ROLE_CHEF, true)
                            .orElseThrow(() -> new NotFoundException("Fail! -> Cause: Role not found."));
                    roleSet.add(chefRole);

                    break;
            }
                });

        account.setRole(roleSet);
        Employee employee = employeeRepository.findById(signUpDto.getIdEmployee()).orElseThrow(
                ()-> new NotFoundException("Id not found"));
        account.setEmployee(employee);
        this.accountRepository.save(account);
        return new ResponseDto(HttpStatus.OK.value(), "Create account successful", null);
    }
    public ResponseDto deleteAccount(Integer id){
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        Employee employee = employeeRepository.findByAccountId(id)
                .orElseThrow(()-> new NotFoundException("Employee not found"));
        employeeService.deleteEmployee(employee.getId());
        account.setEnable(false);
        accountRepository.save(account);
        return new ResponseDto(HttpStatus.OK.value(), "Delete account successful", null);
    }
    @Transactional
    public ResponseDto getAccount(String username){
        Account account = accountRepository.findByUsernameAndEnable(username, true)
                .orElseThrow(()-> new NotFoundException("Username not found"));
        AccountResponseDto accountResponseDto = mapperObject.AccountEntityToAccountResponseDto(account);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", accountResponseDto);
    }


    @Transactional
    @Override
    public PagingResponseDto getAllAccountPaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<AccountResponseDto> responseDtos = new ArrayList<>();
        Page<Account> accountPage = accountRepository.findAllByEnable(true, pageable);
        //get Account
        accountPage.forEach(element->{
            AccountResponseDto accountResponseDto = mapperObject.AccountEntityToAccountResponseDto(element);
            Set<Role> role = element.getRole();
            Set<RoleDto> roleDtoSet = new HashSet<>();
            //get set<Role>
            role.forEach(elementRole->{
                RoleDto roleDto = mapperObject.RoleEntityToRoleDTO(elementRole);
                roleDto.setName(elementRole.getName().name());
                roleDtoSet.add(roleDto);
            });
            accountResponseDto.setRole(roleDtoSet);
            accountResponseDto.setIdEmployee(element.getEmployee().getId());
            responseDtos.add(accountResponseDto);});
        Page<AccountResponseDto> accountResponseDtos = new PageImpl<>(responseDtos, pageable, accountPage.getTotalElements());
        return new PagingResponseDto<>(
                accountResponseDtos.getContent(), accountResponseDtos.getTotalElements(), accountResponseDtos.getTotalPages(),
                accountResponseDtos.getPageable());
    }
    @Transactional
    public ResponseDto getAllAccount(){
        List<Account> accounts = accountRepository.findAllByEnable(true);
        List<AccountResponseDto> accountResponseDtos = new ArrayList<>();
        accounts.forEach(element->{
            AccountResponseDto accountResponseDto = mapperObject.AccountEntityToAccountResponseDto(element);
            Set<Role> role = element.getRole();
            Set<RoleDto> roleDtoSet = new HashSet<>();
            //get set<Role>
            role.forEach(elementRole->{
                RoleDto roleDto = mapperObject.RoleEntityToRoleDTO(elementRole);
                roleDto.setName(elementRole.getName().name());
                roleDtoSet.add(roleDto);
            });
            accountResponseDto.setRole(roleDtoSet);
            accountResponseDto.setIdEmployee(element.getEmployee().getId());
            accountResponseDtos.add(accountResponseDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All account",accountResponseDtos);
    }

}
