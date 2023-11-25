package com.timesheet.dto.request_body;

import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;

public class CheckInRequestDto {
    private Integer month;
    private Integer year;
    private Integer employeeId;
    private CheckInStatus status;

    public CheckInRequestDto() {
    }

    public CheckInRequestDto(Integer month, Integer year, Integer employeeId, CheckInStatus status) {
        this.month = month;
        this.year = year;
        this.employeeId = employeeId;
        this.status = status;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public CheckInStatus getStatus() {
        return status;
    }

    public void setStatus(CheckInStatus status) {
        this.status = status;
    }
}
