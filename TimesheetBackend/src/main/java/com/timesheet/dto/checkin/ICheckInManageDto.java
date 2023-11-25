package com.timesheet.dto.checkin;

public interface ICheckInManageDto {
    Integer getId();
    String getFullName();
    String getEmail();
    String getDepartmentName();
    int getCountCheckInLate();
    int getCountNotCheckOut();
    int getCountNotCheckIn();
}
