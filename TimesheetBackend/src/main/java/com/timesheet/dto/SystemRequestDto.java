package com.timesheet.dto;

public class SystemRequestDto {
    private Integer employeeId;
    private String password;

    public SystemRequestDto() {}

    public SystemRequestDto(Integer employeeId, String password) {
        this.employeeId = employeeId;
        this.password = password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
