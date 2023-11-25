package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Account;

public interface RefreshTokenService {
    Account findByToken(String refreshToken);
}
