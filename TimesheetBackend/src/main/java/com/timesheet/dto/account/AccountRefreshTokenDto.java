package com.timesheet.dto.account;

import com.timesheet.dto.RoleTokenDto;

import java.util.Date;
import java.util.List;

public class AccountRefreshTokenDto {
    private String refreshToken;
    private Date expiredDate;
    private String username;
    private String password;
    private List<RoleTokenDto> roles;

    public AccountRefreshTokenDto() {
    }

    public AccountRefreshTokenDto(String refreshToken, Date expiredDate, String username, String password, List<RoleTokenDto> roles) {
        this.refreshToken = refreshToken;
        this.expiredDate = expiredDate;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleTokenDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleTokenDto> roles) {
        this.roles = roles;
    }
}
