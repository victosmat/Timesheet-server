package com.timesheet.configuration.security;

import com.manage.employeemanagementmodel.entity.Account;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("Cannot find any user account with username: " + username);
        } else {
            return new CustomUserDetails(account);
        }
    }
}
