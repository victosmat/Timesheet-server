package com.timesheet.dto.project;

import com.manage.employeemanagementmodel.entity.Role;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.RoleProjectType;

import java.util.List;

public class EmployeeProjectDetailDto {
    private Integer id;
    private Integer employeeId;
    private String employeeName;
    private String email;
    private DepartmentLevelStatus departmentLevelStatus;
    private RoleProjectType roleProjectType;
    private String roles;

    public EmployeeProjectDetailDto(Integer id, Integer employeeId, String employeeName, String email, DepartmentLevelStatus departmentLevelStatus, RoleProjectType roleProjectType) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.departmentLevelStatus = departmentLevelStatus;
        this.roleProjectType = roleProjectType;
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
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartmentLevelStatus getDepartmentLevelStatus() {
        return departmentLevelStatus;
    }

    public void setDepartmentLevelStatus(DepartmentLevelStatus departmentLevelStatus) {
        this.departmentLevelStatus = departmentLevelStatus;
    }

    public RoleProjectType getRoleProjectType() {
        return roleProjectType;
    }

    public void setRoleProjectType(RoleProjectType roleProjectType) {
        this.roleProjectType = roleProjectType;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
