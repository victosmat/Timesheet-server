package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
import com.manage.employeemanagementmodel.exception.AbsenceRequestNotFoundException;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.absence.AbsenceManageViewDto;
import com.timesheet.dto.absence.AbsenceViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AbsenceService {
    Map<AbsenceStatus, List<AbsenceDto>> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);

    void saveAbsenceRequest(AbsenceDto absenceDto);

    boolean deletePendingAbsenceRequest(Integer absenceRequest) throws AbsenceRequestNotFoundException;

    AbsenceDto getAbsenceByDate(LocalDate date);

    void updateAbsenceStatus(Integer absenceId, AbsenceStatus status);

    Page<AbsenceDto> listAllAbsenceRequestInMonthAndYearOfEmployee(Integer month, Integer year, AbsenceStatus absenceStatus, Integer employeeId, Pageable pageable);

    List<LocalDate> ListAllDayAbsenceInParticularMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);

    List<AbsenceViewDto> getAbsenceByDateAndEmployee(LocalDate date, Integer employeeId);

    AbsenceDto findFormById(Integer id);

    Map<AbsenceStatus, List<AbsenceViewDto>> listAllAbsenceOfStaffInParticularMonthAndYear(int staffId, int month, int year);

    void updatePendingAbsenceStatus(int absenceId, AbsenceStatus status);

    List<AbsenceDto> listAllAbsenceRequestInMonthAndYear(Integer monthNumber, Integer yearNumber, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff);

    List<LocalDate> ListAllDayAbsenceInParticularMonthAndYear(Integer month, Integer year, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff);

    List<AbsenceManageViewDto> getAbsenceByDateAllEmployee(LocalDate localDate, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff);

    void updatePunishment(Integer absenceId, Boolean punishmentStatus);
}
