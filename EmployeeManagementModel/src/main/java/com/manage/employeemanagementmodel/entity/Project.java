package com.manage.employeemanagementmodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.manage.employeemanagementmodel.entity.enums.ProjectType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "project_type")
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @Column(name = "project_status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<EmployeeProject> employeeProjects;

    public Project() {
    }

    public Project(Integer id, String code, String name, String description, ProjectType projectType, ProjectStatus projectStatus, LocalDate startDate, LocalDate endDate, List<EmployeeProject> employeeProjects) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeProjects = employeeProjects;
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

    public List<EmployeeProject> getEmployeeProjects() {
        return employeeProjects;
    }

    public void setEmployeeProjects(List<EmployeeProject> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }
}
