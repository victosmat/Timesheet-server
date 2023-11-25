package com.timesheet.dto.project;

import com.manage.employeemanagementmodel.entity.enums.RoleProjectType;

public class EmployeeProjectSaveDto {
    private Integer id;
    private RoleProjectType roleProjectType;
    private Integer employeeId;

    public EmployeeProjectSaveDto() {
    }

    public EmployeeProjectSaveDto(Integer id, RoleProjectType roleProjectType, Integer employeeId) {
        this.id = id;
        this.roleProjectType = roleProjectType;
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleProjectType getRoleProjectType() {
        return roleProjectType;
    }

    public void setRoleProjectType(RoleProjectType roleProjectType) {
        this.roleProjectType = roleProjectType;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
