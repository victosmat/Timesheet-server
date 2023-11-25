package com.timesheet.dto.project;

import java.time.LocalDate;

public class ProfileDto {
    private String name;
    private String username;
    private LocalDate birthDate;
    private String departmentName;
    private String jobDepartmentName;

    public ProfileDto(String name, String username, LocalDate birthDate, String departmentName, String jobDepartmentName) {
        this.name = name;
        this.username = username;
        this.birthDate = birthDate;
        this.departmentName = departmentName;
        this.jobDepartmentName = jobDepartmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobDepartmentName() {
        return jobDepartmentName;
    }

    public void setJobDepartmentName(String jobDepartmentName) {
        this.jobDepartmentName = jobDepartmentName;
    }
}
