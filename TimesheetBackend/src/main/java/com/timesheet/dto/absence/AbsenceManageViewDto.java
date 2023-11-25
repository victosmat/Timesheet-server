package com.timesheet.dto.absence;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

import java.time.LocalDate;

public class AbsenceManageViewDto {
    private Integer id;
    private String fullName;
    private String email;
    private String departmentName;
    private TypeTimeOff typeTimeOff;
    private Double timeOff;
    private String reason;
    private LocalDate dateRequest;
    private AbsenceStatus status;
    private Boolean punishmentStatus;

    public AbsenceManageViewDto(Integer id, String fullName, String email, String departmentName, TypeTimeOff typeTimeOff, Double timeOff, String reason, LocalDate dateRequest, AbsenceStatus status, Boolean punishmentStatus) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.departmentName = departmentName;
        this.typeTimeOff = typeTimeOff;
        this.timeOff = timeOff;
        this.reason = reason;
        this.dateRequest = dateRequest;
        this.status = status;
        this.punishmentStatus = punishmentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public TypeTimeOff getTypeTimeOff() {
        return typeTimeOff;
    }

    public void setTypeTimeOff(TypeTimeOff typeTimeOff) {
        this.typeTimeOff = typeTimeOff;
    }

    public Double getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(Double timeOff) {
        this.timeOff = timeOff;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public AbsenceStatus getStatus() {
        return status;
    }

    public void setStatus(AbsenceStatus status) {
        this.status = status;
    }

    public Boolean getPunishmentStatus() {
        return punishmentStatus;
    }

    public void setPunishmentStatus(Boolean punishmentStatus) {
        this.punishmentStatus = punishmentStatus;
    }
}
