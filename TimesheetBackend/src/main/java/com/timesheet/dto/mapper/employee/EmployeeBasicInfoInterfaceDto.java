package com.timesheet.dto.mapper.employee;

import com.manage.employeemanagementmodel.entity.enums.Gender;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface EmployeeBasicInfoInterfaceDto {
    String getFirstName();
    String getLastName();
    Gender getGender();
    String getEmail();
    LocalDate getBirthDate();
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
}
