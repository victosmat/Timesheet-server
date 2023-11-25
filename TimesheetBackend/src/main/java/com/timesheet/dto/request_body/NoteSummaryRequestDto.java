package com.timesheet.dto.request_body;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;

import java.util.List;

public class NoteSummaryRequestDto {
    private Integer month;
    private Integer year;
    private Integer employeeId;
    private List<TimeSheetStatus> statuses;

    public NoteSummaryRequestDto() {
    }

    public NoteSummaryRequestDto(Integer month, Integer year, Integer employeeId, List<TimeSheetStatus> statuses) {
        this.month = month;
        this.year = year;
        this.employeeId = employeeId;
        this.statuses = statuses;
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

    public List<TimeSheetStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<TimeSheetStatus> statuses) {
        this.statuses = statuses;
    }
}
