package com.timesheet.dto.absence;

import com.manage.employeemanagementmodel.entity.PunishmentType;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

import java.time.LocalDate;

public class AbsenceViewDto {
    private Integer id;
    private TypeTimeOff typeTimeOff;
    private Double timeOff;
    private String reason;
    private LocalDate dateRequest;
    private AbsenceStatus status;
    private Boolean punishmentStatus;

    public AbsenceViewDto() {
    }

    public AbsenceViewDto(Integer id, TypeTimeOff typeTimeOff, Double timeOff, String reason, LocalDate dateRequest, AbsenceStatus status, Boolean punishmentStatus) {
        this.id = id;
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

    public AbsenceStatus getStatus() {
        return status;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
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
