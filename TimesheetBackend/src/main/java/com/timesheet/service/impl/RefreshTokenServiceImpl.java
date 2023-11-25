package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Account;
import com.timesheet.repository.AccountRepository;
import com.timesheet.repository.RefreshTokenRepository;
import com.timesheet.service.RefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, AccountRepository accountRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByToken(String refreshToken) {
        return accountRepository.findByRefreshToken(refreshToken);
    }
}
