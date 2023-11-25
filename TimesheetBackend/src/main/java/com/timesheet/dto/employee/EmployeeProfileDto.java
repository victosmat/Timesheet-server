package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.Gender;

import java.time.LocalDate;

public class EmployeeProfileDto {
	private String fullName;
	private Gender gender;
	private LocalDate hiringDate;
	private String departmentName;
	private String buddyName;
	private String accountEmail;
	
	public EmployeeProfileDto(String fullName, Gender gender, LocalDate hiringDate, String departmentName,
							  String buddyName, String accountEmail) {
		this.fullName = fullName;
		this.gender = gender;
		this.hiringDate = hiringDate;
		this.departmentName = departmentName;
		this.buddyName = buddyName;
		this.accountEmail = accountEmail;
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
	public LocalDate getHiringDate() {
		return hiringDate;
	}
	public void setHiringDate(LocalDate hiringDate) {
		this.hiringDate = hiringDate;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBuddyName() {
		return buddyName;
	}
	public void setBuddyName(String buddyName) {
		this.buddyName = buddyName;
	}
	public String getAccountEmail() {
		return accountEmail;
	}
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	
	
}
