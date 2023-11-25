package com.timesheet.dto.checkin;

import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;

public class CheckinPunishmentResDto {
    private Integer id;
    private String date;
    private String timeCheckin;
    private String timeCheckout;
    private CheckInStatus status;
    private String punishmentTypeDes;
    private Double punishmentMoney;
    private String complain;
    private String complainReply;
    private Boolean isDeleted;

    public CheckinPunishmentResDto(Integer id, String date, String timeCheckin, String timeCheckout, CheckInStatus status, String punishmentTypeDes, Double punishmentMoney, String complain, String complainReply, Boolean isDeleted) {
        this.id = id;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(String timeCheckin) {
        this.timeCheckin = timeCheckin;
    }

    public String getTimeCheckout() {
        return timeCheckout;
    }

    public void setTimeCheckout(String timeCheckout) {
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
