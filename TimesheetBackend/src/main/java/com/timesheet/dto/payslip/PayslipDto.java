package com.timesheet.dto.payslip;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.checkin.CheckinPunishmentDto;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class PayslipDto {
    private Integer id;
    private Integer employeeId;
    private String fullName;
    private String email;
    private String departmentName;
    private DepartmentLevelStatus departmentLevelStatus;
    private String payDay;
    private Double totalSalary;
    private Boolean paymentStatus;

    public PayslipDto() {
    }

    public PayslipDto(Integer id, Integer employeeId, String fullName, String email, String departmentName, DepartmentLevelStatus departmentLevelStatus, String payDay, Double totalSalary, Boolean paymentStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.departmentName = departmentName;
        this.departmentLevelStatus = departmentLevelStatus;
        this.payDay = payDay;
        this.totalSalary = totalSalary;
        this.paymentStatus = paymentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentLevelStatus getDepartmentLevelStatus() {
        return departmentLevelStatus;
    }

    public void setDepartmentLevelStatus(DepartmentLevelStatus departmentLevelStatus) {
        this.departmentLevelStatus = departmentLevelStatus;
    }

    public String getPayDay() {
        return payDay;
    }

    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
