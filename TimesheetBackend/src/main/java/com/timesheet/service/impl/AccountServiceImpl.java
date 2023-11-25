package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Account;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.repository.AccountRepository;
import com.timesheet.service.AccountService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void logout() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = customUserDetails.getAccount();
        account.setRefreshToken(null);
        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Integer getAccountByEmployeeId(Integer id) {
        return accountRepository.getAccountByEmployeeId(id);
    }
}
