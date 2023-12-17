package com.timesheet.dto.project;

import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;

import java.time.LocalDate;

public class ProjectViewDto {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private String pmName;
    private Long totalEmployee;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus projectStatus;

    public ProjectViewDto(Integer id, String code, String name, String description, LocalDate startDate, LocalDate endDate, String pmName, Long totalEmployee, ProjectStatus projectStatus) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
