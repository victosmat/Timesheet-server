package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.Gender;

import java.time.LocalDate;

public class EmployeeManageDto {
    private Integer id;
    private String fullName;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate hiringDate;
    private String email;
    private String buddyName;
    private String departmentName;
    private DepartmentLevelStatus levelStatus;
    private long salary;
    private boolean isEnabled;

    public EmployeeManageDto(Integer id, String fullName, Gender gender, LocalDate birthDate, LocalDate hiringDate, String email, String buddyName, String departmentName, DepartmentLevelStatus levelStatus, long salary, boolean isEnabled) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.email = email;
        this.buddyName = buddyName;
        this.departmentName = departmentName;
        this.levelStatus = levelStatus;
        this.salary = salary;
        this.isEnabled = isEnabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuddyName() {
        return buddyName;
    }

    public void setBuddyName(String buddyName) {
        this.buddyName = buddyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentLevelStatus getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(DepartmentLevelStatus levelStatus) {
        this.levelStatus = levelStatus;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
