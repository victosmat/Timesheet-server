package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.Gender;

import java.time.LocalDate;

public class EmployeeSaveDto {
    private Integer id;
    private String firstName;
    private Gender gender;
    private LocalDate birthDate;
    private String email;
    private Integer bankId;
    private String lastName;
    private String bankName;
    private String bankNumber;
    private LocalDate hiringDate;
    private Integer buddyId;
    private Integer departmentId;
    private String username;
    private String password;
    private Integer jobDepartmentId;
    private DepartmentLevelStatus level;

    public EmployeeSaveDto() {
    }

    public EmployeeSaveDto(Integer id, String firstName, Gender gender, LocalDate birthDate, String email, Integer bankId, String lastName, String bankName, String bankNumber, LocalDate hiringDate, Integer buddyId, Integer departmentId, String username, String password, Integer jobDepartmentId, DepartmentLevelStatus level) {
        this.id = id;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.bankId = bankId;
        this.lastName = lastName;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.hiringDate = hiringDate;
        this.buddyId = buddyId;
        this.departmentId = departmentId;
        this.username = username;
        this.password = password;
        this.jobDepartmentId = jobDepartmentId;
        this.level = level;
    }

    public  Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public Integer getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(Integer buddyId) {
        this.buddyId = buddyId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public Integer getJobDepartmentId() {
        return jobDepartmentId;
    }

    public void setJobDepartmentId(Integer jobDepartmentId) {
        this.jobDepartmentId = jobDepartmentId;
    }

    public DepartmentLevelStatus getLevel() {
        return level;
    }

    public void setLevel(DepartmentLevelStatus level) {
        this.level = level;
    }
}
