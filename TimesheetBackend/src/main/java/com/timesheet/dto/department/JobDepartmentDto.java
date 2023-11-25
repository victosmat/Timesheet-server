package com.timesheet.dto.department;

import jakarta.persistence.Column;

public class JobDepartmentDto {
    private Integer id;
    private String jobDepartment;
    private String name;
    private String description;
    private long salaryRange;

    public JobDepartmentDto() {
    }

    public JobDepartmentDto(Integer id, String jobDepartment, String name, String description, long salaryRange) {
        this.id = id;
        this.jobDepartment = jobDepartment;
        this.name = name;
        this.description = description;
        this.salaryRange = salaryRange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
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

    public long getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(long salaryRange) {
        this.salaryRange = salaryRange;
    }
}
