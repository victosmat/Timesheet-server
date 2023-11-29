package com.timesheet.dto.absence;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

import java.time.LocalDate;

public class AbsenceDto {
    private Integer id;
    private String reason;
    private Integer employeeId;
    private String fullName;
    private LocalDate dateRequest;
    private LocalDate dateSubmit;
    private TypeTimeOff typeTimeOff;
    private Double timeOff;
    private AbsenceStatus status;
    private Boolean punishmentStatus;

    public AbsenceDto() {
    }

    public AbsenceDto(Integer id, String reason, Integer employeeId, String fullName, LocalDate dateRequest, LocalDate dateSubmit, TypeTimeOff typeTimeOff, Double timeOff, AbsenceStatus status, Boolean punishmentStatus) {
        this.id = id;
        this.reason = reason;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.dateRequest = dateRequest;
        this.dateSubmit = dateSubmit;
        this.typeTimeOff = typeTimeOff;
        this.timeOff = timeOff;
        this.status = status;
        this.punishmentStatus = punishmentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
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
