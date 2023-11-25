package com.timesheet.dto.project;

import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.manage.employeemanagementmodel.entity.enums.ProjectType;

import java.time.LocalDate;
import java.util.List;

public class ProjectDetailDto {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private ProjectType projectType;
    private ProjectStatus projectStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    public List<EmployeeProjectDetailDto> projectEmployeeSaveDtos;

    public ProjectDetailDto(Integer id, String code, String name, String description, ProjectType projectType, ProjectStatus projectStatus, LocalDate startDate, LocalDate endDate, List<EmployeeProjectDetailDto> projectEmployeeSaveDtos) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectEmployeeSaveDtos = projectEmployeeSaveDtos;
    }

    public ProjectDetailDto(Integer id, String code, String name, String description, ProjectType projectType, ProjectStatus projectStatus, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
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

    public List<EmployeeProjectDetailDto> getProjectEmployeeSaveDtos() {
        return projectEmployeeSaveDtos;
    }

    public void setProjectEmployeeSaveDtos(List<EmployeeProjectDetailDto> projectEmployeeSaveDtos) {
        this.projectEmployeeSaveDtos = projectEmployeeSaveDtos;
    }
}
