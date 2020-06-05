package coffeesystem.configuration.security.service;


import coffeesystem.model.Account;
import coffeesystem.repository.AccountRepository;
import coffeesystem.repository.RoleRepository;
import coffeesystem.util.MapperObject;
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

        Account account = accountRepository.findRoleUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return UserPrinciple.build(account);
    }
}
