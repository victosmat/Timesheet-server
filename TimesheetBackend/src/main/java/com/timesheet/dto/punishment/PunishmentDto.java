package com.timesheet.dto.punishment;

import java.time.LocalDate;

public class PunishmentDto {
    private Integer id;
    private String complain;
    private String complainReply;
    private LocalDate date;
    private String punishmentTypeName;
    private Integer employeeId;
    private Boolean isDeleted;

    public PunishmentDto(Integer id, String complain, String complainReply, LocalDate date, String punishmentTypeName, Integer employeeId, Boolean isDeleted) {
        this.id = id;
        this.complain = complain;
        this.complainReply = complainReply;
        this.date = date;
        this.punishmentTypeName = punishmentTypeName;
        this.employeeId = employeeId;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getComplainReply() {
        return complainReply;
    }

    public void setComplainReply(String complainReply) {
        this.complainReply = complainReply;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPunishmentTypeName() {
        return punishmentTypeName;
    }

    public void setPunishmentTypeName(String punishmentTypeName) {
        this.punishmentTypeName = punishmentTypeName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
