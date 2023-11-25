package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Employee;
import jakarta.mail.MessagingException;

public interface EmailService {
	void sendEmailToPM(Employee employee, String type) throws MessagingException;

	void sendEmailToEmployee(Employee employee, String independenceDay);
}
