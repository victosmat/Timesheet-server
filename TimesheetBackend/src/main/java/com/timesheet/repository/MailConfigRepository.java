package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.MailConfig;
import com.manage.employeemanagementmodel.entity.enums.MailType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailConfigRepository extends JpaRepository<MailConfig, Integer> {

	MailConfig findByType(MailType notifyEmployee);
	
}
