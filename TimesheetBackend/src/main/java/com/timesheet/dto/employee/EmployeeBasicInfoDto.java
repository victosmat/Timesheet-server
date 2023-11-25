package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.Gender;

import java.time.LocalDate;

public class EmployeeBasicInfoDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String email;

    public EmployeeBasicInfoDto(String firstName, String lastName, Gender gender, LocalDate birthDate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
