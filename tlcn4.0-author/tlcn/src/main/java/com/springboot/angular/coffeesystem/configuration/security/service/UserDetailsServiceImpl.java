package com.springboot.angular.coffeesystem.configuration.security.service;

import com.springboot.angular.coffeesystem.model.Account;
import com.springboot.angular.coffeesystem.repository.RoleRepository;
import com.springboot.angular.coffeesystem.repository.AccountRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MapperObject mapperObject;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Account account = accountRepository.findRoleUsername(username);
        return UserPrinciple.build(account);
    }
}
