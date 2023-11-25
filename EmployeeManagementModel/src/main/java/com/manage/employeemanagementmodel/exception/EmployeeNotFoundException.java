package com.manage.employeemanagementmodel.exception;

import java.io.Serial;

public class EmployeeNotFoundException extends Exception {
	@Serial
	private static final long serialVersionUID = 3728920846267560880L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
