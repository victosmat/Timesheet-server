package com.timesheet.dto.checkin;

import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CheckinPunishmentDto {
    private Integer id;
    private LocalDateTime currentDate;
    private LocalDateTime timeCheckin;
    private LocalDateTime timeCheckout;
    private CheckInStatus status;
    private String punishmentTypeDes;
    private Double punishmentMoney;
    private String complain;
    private String complainReply;
    private Boolean isDeleted;

    public CheckinPunishmentDto(Integer id, LocalDateTime currentDate, LocalDateTime timeCheckin, LocalDateTime timeCheckout, CheckInStatus status, String punishmentTypeDes, Double punishmentMoney, String complain, String complainReply, Boolean isDeleted) {
        this.id = id;
        this.currentDate = currentDate;
        this.timeCheckin = timeCheckin;
        this.timeCheckout = timeCheckout;
        this.status = status;
        this.punishmentTypeDes = punishmentTypeDes;
        this.punishmentMoney = punishmentMoney;
        this.complain = complain;
        this.complainReply = complainReply;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDateTime getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(LocalDateTime timeCheckin) {
        this.timeCheckin = timeCheckin;
    }

    public LocalDateTime getTimeCheckout() {
        return timeCheckout;
    }

    public void setTimeCheckout(LocalDateTime timeCheckout) {
        this.timeCheckout = timeCheckout;
    }

    public CheckInStatus getStatus() {
        return status;
    }

    public void setStatus(CheckInStatus status) {
        this.status = status;
    }

    public String getPunishmentTypeDes() {
        return punishmentTypeDes;
    }

    public void setPunishmentTypeDes(String punishmentTypeDes) {
        this.punishmentTypeDes = punishmentTypeDes;
    }

    public Double getPunishmentMoney() {
        return punishmentMoney;
    }

    public void setPunishmentMoney(Double punishmentMoney) {
        this.punishmentMoney = punishmentMoney;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
