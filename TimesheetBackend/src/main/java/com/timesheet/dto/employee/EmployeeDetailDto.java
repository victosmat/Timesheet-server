package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.Gender;


import java.time.LocalDate;

public class EmployeeDetailDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String birthDate;
    private String email;

    private String bankName;
    private String bankNumber;
    private String photo;
    private String hiringDate;
    private String buddyName;
    private String departmentName;
    private String username;
    private String jobDepartment;
    private long jobDepartmentSalaryRange;
    private DepartmentLevelStatus employeeLevelStatus;

    public EmployeeDetailDto(Integer id, String firstName, String lastName, Gender gender, String birthDate, String email, String bankName, String bankNumber, String photo, String hiringDate, String buddyName, String departmentName, String username, String jobDepartment, long jobDepartmentSalaryRange, DepartmentLevelStatus employeeLevelStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.photo = photo;
        this.hiringDate = hiringDate;
        this.buddyName = buddyName;
        this.departmentName = departmentName;
        this.username = username;
        this.jobDepartment = jobDepartment;
        this.jobDepartmentSalaryRange = jobDepartmentSalaryRange;
        this.employeeLevelStatus = employeeLevelStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
    }

    public long getJobDepartmentSalaryRange() {
        return jobDepartmentSalaryRange;
    }

    public void setJobDepartmentSalaryRange(long jobDepartmentSalaryRange) {
        this.jobDepartmentSalaryRange = jobDepartmentSalaryRange;
    }

    public DepartmentLevelStatus getEmployeeLevelStatus() {
        return employeeLevelStatus;
    }

    public void setEmployeeLevelStatus(DepartmentLevelStatus employeeLevelStatus) {
        this.employeeLevelStatus = employeeLevelStatus;
    }
}
