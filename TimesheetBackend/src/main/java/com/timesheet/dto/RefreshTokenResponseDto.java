package com.timesheet.dto;

public class RefreshTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Integer employeeId;
    private String username;

    public RefreshTokenResponseDto() {
    }

    public RefreshTokenResponseDto(String accessToken, String refreshToken, Integer employeeId, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.employeeId = employeeId;
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
