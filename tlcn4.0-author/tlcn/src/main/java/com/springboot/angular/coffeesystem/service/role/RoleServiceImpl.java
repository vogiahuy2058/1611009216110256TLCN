package com.springboot.angular.coffeesystem.service.role;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.RecipeDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.RoleDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Account;
import com.springboot.angular.coffeesystem.model.Role;
import com.springboot.angular.coffeesystem.repository.AccountRepository;
import com.springboot.angular.coffeesystem.repository.RoleRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MapperObject mapperObject;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public ResponseDto getAllRole(){
        List<Role> roles = roleRepository.findAllByEnable(true);
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(element->{
            RoleDto roleDto = mapperObject.RoleEntityToRoleDTO(element);
            roleDto.setName(element.getName().name());
            roleDtos.add(roleDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All role", roleDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllRolePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<RoleDto> roleDtos = new ArrayList<>();
        Page<Role> rolePage = roleRepository.findAllByEnable(true, pageable);
        rolePage.forEach(element->{
            RoleDto roleDto = mapperObject.RoleEntityToRoleDTO(element);
            roleDto.setName(element.getName().name());
            roleDtos.add(roleDto);});
        Page<RoleDto> roleDtoPage = new PageImpl<>(roleDtos, pageable,
                rolePage.getTotalElements() );
        return new PagingResponseDto<>(
                roleDtoPage.getContent(), roleDtoPage.getTotalElements(), roleDtoPage.getTotalPages(),
                roleDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getRoleById(Integer id){
        Role role = roleRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        RoleDto roleDto = mapperObject.RoleEntityToRoleDTO(role);
        roleDto.setName(role.getName().name());
        return new ResponseDto(HttpStatus.OK.value(), "Successful", roleDto);
    }
    public ResponseDto deleteRole(Integer id){
        Role role = roleRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        role.setEnable(false);
        roleRepository.save(role);
        return new ResponseDto(HttpStatus.OK.value(), "Delete account successful", null);
    }


}
