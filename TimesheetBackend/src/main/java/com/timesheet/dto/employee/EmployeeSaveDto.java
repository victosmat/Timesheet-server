package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.Gender;

import java.time.LocalDate;
public class EmployeeSaveDto {
    private Integer id;
    private String firstName;
    private Gender gender;
    private LocalDate birthday;
    private String email;
    private String lastName;
    private String bankName;
    private String bankNumber;
    private LocalDate hiringDate;
    private String buddyName;
    private String departmentName;
    private String username;
    private String password;
    private String jobDepartment;
    private DepartmentLevelStatus level;

    public EmployeeSaveDto() {
    }

    public EmployeeSaveDto(Integer id, String firstName, Gender gender, LocalDate birthday, String email, String lastName, String bankName, String bankNumber, LocalDate hiringDate, String buddyName, String departmentName, String username, String password, String jobDepartment, DepartmentLevelStatus level) {
        this.id = id;
        this.firstName = firstName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.lastName = lastName;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.hiringDate = hiringDate;
        this.buddyName = buddyName;
        this.departmentName = departmentName;
        this.username = username;
        this.password = password;
        this.jobDepartment = jobDepartment;
        this.level = level;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
    }

    public DepartmentLevelStatus getLevel() {
        return level;
    }

    public void setLevel(DepartmentLevelStatus level) {
        this.level = level;
    }
}
