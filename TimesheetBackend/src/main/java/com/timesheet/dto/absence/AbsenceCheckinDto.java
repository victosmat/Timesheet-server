package com.timesheet.dto.absence;

import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;

import java.time.LocalDate;

public class AbsenceCheckinDto {
    private Integer id;
    private LocalDate dateRequest;
    private TypeTimeOff typeTimeOff;
    private Double timeOff;

    public AbsenceCheckinDto() {
    }

    public AbsenceCheckinDto(Integer id, LocalDate dateRequest, TypeTimeOff typeTimeOff, Double timeOff) {
        this.id = id;
        this.dateRequest = dateRequest;
        this.typeTimeOff = typeTimeOff;
        this.timeOff = timeOff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
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
}
