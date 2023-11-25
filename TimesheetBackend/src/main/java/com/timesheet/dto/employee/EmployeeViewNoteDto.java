package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;

import java.util.Objects;

public class EmployeeViewNoteDto {
    private Integer id;
    private String employeeName;
    private DepartmentLevelStatus employeeLevel;
    private String employeeDepartment;

    public EmployeeViewNoteDto(Integer id, String employeeName, DepartmentLevelStatus employeeLevel, String employeeDepartment) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeLevel = employeeLevel;
        this.employeeDepartment = employeeDepartment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public DepartmentLevelStatus getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(DepartmentLevelStatus employeeLevel) {
        this.employeeLevel = employeeLevel;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeViewNoteDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmployeeName(), that.getEmployeeName()) && getEmployeeLevel() == that.getEmployeeLevel() && Objects.equals(getEmployeeDepartment(), that.getEmployeeDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployeeName(), getEmployeeLevel(), getEmployeeDepartment());
    }
}
