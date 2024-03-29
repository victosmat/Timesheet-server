package com.timesheet.dto.bonus;

import java.time.LocalDate;

public class EmployeeBonusDto {
    private Integer id;
    private Integer bonusId;
    private String name;
    private String description;
    private LocalDate dateBonus;
    private String reason;
    private Long gratuity;

    public EmployeeBonusDto(Integer id, Integer bonusId, String name, String description, LocalDate dateBonus, String reason, Long gratuity) {
        this.id = id;
        this.bonusId = bonusId;
        this.name = name;
        this.description = description;
        this.dateBonus = dateBonus;
        this.reason = reason;
        this.gratuity = gratuity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBonusId() {
        return bonusId;
    }

    public void setBonusId(Integer bonusId) {
        this.bonusId = bonusId;
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

    public LocalDate getDateBonus() {
        return dateBonus;
    }

    public void setDateBonus(LocalDate dateBonus) {
        this.dateBonus = dateBonus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getGratuity() {
        return gratuity;
    }

    public void setGratuity(Long gratuity) {
        this.gratuity = gratuity;
    }
}
