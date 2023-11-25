package com.manage.employeemanagementmodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee_bonus")
public class EmployeeBonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date_bonus")
    private LocalDate dateBonus;
    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "bonus_id")
    private Bonus bonus;

    public EmployeeBonus() {
    }

    public EmployeeBonus(Integer id, LocalDate dateBonus, String reason, Employee employee, Bonus bonus) {
        this.id = id;
        this.dateBonus = dateBonus;
        this.reason = reason;
        this.employee = employee;
        this.bonus = bonus;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
}
