package com.timesheet.dto.punishment;

import jakarta.persistence.Column;

public class PunishmentTypeDto {
    private Integer id;
    private String name;
    private String description;
    private Double punishmentMoney;

    public PunishmentTypeDto() {
    }

    public PunishmentTypeDto(Integer id, String name, String description, Double punishmentMoney) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.punishmentMoney = punishmentMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPunishmentMoney() {
        return punishmentMoney;
    }

    public void setPunishmentMoney(Double punishmentMoney) {
        this.punishmentMoney = punishmentMoney;
    }
}
