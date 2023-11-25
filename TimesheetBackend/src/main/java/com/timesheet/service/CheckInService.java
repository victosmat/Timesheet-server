package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.checkin.CheckinPunishmentDto;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import com.timesheet.dto.checkin.ICheckInManageDto;
import com.timesheet.dto.request_body.CheckInRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckInService {
    List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request);
    boolean saveCheckPointInDay(Integer employeeId, LocalDateTime checkPointTime);

    List<Integer> getAllEmployeeNotAbsenceInDay(LocalDate localDate);

    void checkPunishmentCheckin(Integer employeeId, LocalDate localDate);

    CheckIn findById(Integer id);

    Object updateStatus(Integer id, String status);

    List<CheckinPunishmentResDto> getCheckinOfEmployeeAndPunishment(Integer employeeId, int month, int year);

    void saveInitDate(Integer id, LocalDateTime localDateTime);

    int checkCurrentDateIsExist(LocalDateTime localDateTime);

    List<CheckinPunishmentResDto> getCheckinOfEmployeeAndPunishmentByStatus(Integer employeeId, CheckInStatus status, int month, int year, Boolean isComplain);

    List<ICheckInManageDto> getAllCheckinAndPunishment(String keyword, Integer month, Integer year, String department);
}
