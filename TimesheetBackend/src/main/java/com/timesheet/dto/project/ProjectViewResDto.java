package com.timesheet.dto.project;

import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;

public class ProjectViewResDto {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private String pmName;
    private Long totalEmployee;
    private String startDate;
    private String endDate;
    private ProjectStatus projectStatus;

    public ProjectViewResDto(Integer id, String code, String name, String description, String pmName, Long totalEmployee, String startDate, String endDate, ProjectStatus projectStatus) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.pmName = pmName;
        this.totalEmployee = totalEmployee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatus = projectStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public Long getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(Long totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
