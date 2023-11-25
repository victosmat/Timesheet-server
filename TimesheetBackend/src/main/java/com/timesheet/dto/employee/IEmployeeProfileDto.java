package com.timesheet.dto.employee;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IEmployeeProfileDto {
    Long getID();
    String getFullName();
    String getGender();
    String getBirthDate();
    String getHiringDate();
    String getEmail();
    String getBuddyName();
    String getDepartmentName();
    String getLevelStatus();
    BigDecimal getSalary();
    String getRoles();
    Boolean getIsEnabled();
}

