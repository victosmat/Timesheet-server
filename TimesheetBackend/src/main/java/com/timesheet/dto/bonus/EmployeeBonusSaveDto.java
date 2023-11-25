package com.timesheet.dto.bonus;

import java.time.LocalDate;

public class EmployeeBonusSaveDto {
    private Integer id;
    private LocalDate dateBonus;
    private String reason;

    private Integer employeeId;
    private Integer bonusId;

    public EmployeeBonusSaveDto(Integer id, LocalDate dateBonus, String reason, Integer employeeId, Integer bonusId) {
        this.id = id;
        this.dateBonus = dateBonus;
        this.reason = reason;
        this.employeeId = employeeId;
        this.bonusId = bonusId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getBonusId() {
        return bonusId;
    }

    public void setBonusId(Integer bonusId) {
        this.bonusId = bonusId;
    }
}
