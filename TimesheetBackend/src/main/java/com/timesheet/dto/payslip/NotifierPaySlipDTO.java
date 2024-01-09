package com.timesheet.dto.payslip;

public interface NotifierPaySlipDTO {
    Integer getId();
    String getFullName();
    String getEmail();
    String getDepartmentName();
    String getSalary();
    int getCountCheckInLate();
    int getCountNotCheckOut();
    int getCountNotCheckIn();
    int getCountAbsence();
    int getCountBonus();
}
