package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Employee;

public interface EmailService {
	void sendEmailToPM(Employee employee, String type);

	void sendEmailToEmployee(Employee employee, String type, String password);
}
