package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.absence.AbsenceManageViewDto;
import com.timesheet.dto.absence.AbsenceViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    @Query("SELECT new com.timesheet.dto.absence.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff, absence.timeOff, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff, absence.timeOff, absence.status, absence.punishmentStatus) FROM Absence absence WHERE absence.dateRequest = ?1")
    AbsenceDto getByDate(LocalDate date);

    @Query("UPDATE Absence absence SET absence.status = ?2 WHERE absence.id = ?1")
    @Modifying
    void updateAbsenceStatus(Integer absenceId, AbsenceStatus status);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff,absence.timeOff, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.employee.id = ?3")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);

    @Query("SELECT absence.dateRequest FROM Absence absence WHERE MONTH(absence.dateRequest) = ?1 AND YEAR(absence.dateRequest) = ?2 AND absence.employee.id = ?3")
    List<LocalDate> ListAllDayAbsenceInParticularMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceViewDto(absence.id, absence.typeTimeOff, " +
            "absence.timeOff, absence.reason, absence.dateRequest, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE absence.dateRequest = ?1 " +
            "AND absence.employee.id = ?2")
    List<AbsenceViewDto> getAbsenceByDateAndEmployee(LocalDate date, Integer employeeId);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff,absence.timeOff, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE absence.id = ?1")
    AbsenceDto findFormById(Integer id);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceViewDto(absence.id, absence.typeTimeOff, " +
            " absence.timeOff, absence.reason, absence.dateRequest, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE MONTH(absence.dateSubmit) = ?2 " +
            "AND YEAR(absence.dateSubmit) = ?3 AND absence.employee.id = ?1")
    List<AbsenceViewDto> listAllAbsenceOfStaffInParticularMonth(int staffId, int month, int year);

    @Modifying
    @Query("UPDATE Absence absence SET absence.status = ?2 " +
            "WHERE absence.id = ?1")
    void updatePendingAbsenceStatus(int absenceId, AbsenceStatus status);

    @Query("SELECT e.id FROM Employee e WHERE e.id NOT IN (SELECT a.employee.id FROM Absence a WHERE a.dateRequest = ?1)")
    List<Integer> getAllEmployeeNotAbsenceInDay(LocalDate localDate);


    @Query("SELECT new com.timesheet.dto.absence.AbsenceCheckinDto(absence.id, absence.dateRequest, absence.typeTimeOff, absence.timeOff) " +
            "FROM Absence absence WHERE absence.status = com.manage.employeemanagementmodel.entity.enums.AbsenceStatus.APPROVED " +
            "AND absence.employee.id = ?1 AND absence.dateRequest = ?2")
    List<AbsenceDto> getAbsenceInCurrentDay(Integer id, LocalDate now);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceDto(absence.id, absence.reason, absence.employee.id, absence.dateRequest, absence.dateSubmit, absence.typeTimeOff, absence.timeOff, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE " +
            "   (MONTH(absence.dateRequest) = ?1 OR ?1 IS NULL) " +
            "   AND (YEAR(absence.dateRequest) = ?2 OR ?2 IS NULL) " +
            "   AND (absence.employee.email LIKE %?3% OR ?3 IS NULL) " +
            "   AND (absence.status = ?4 OR ?4 IS NULL) " +
            "   AND (absence.typeTimeOff = ?5 OR ?5 IS NULL)")
    List<AbsenceDto> listAllAbsenceRequestInMonthAndYear(Integer monthNumber, Integer yearNumber, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff);

    @Query("SELECT absence.dateRequest FROM Absence absence WHERE " +
            "   (MONTH(absence.dateRequest) = ?1 OR ?1 IS NULL) " +
            "   AND (YEAR(absence.dateRequest) = ?2 OR ?2 IS NULL) " +
            "   AND (absence.employee.email LIKE %?3% OR ?3 IS NULL) " +
            "   AND (absence.status = ?4 OR ?4 IS NULL) " +
            "   AND (absence.typeTimeOff = ?5 OR ?5 IS NULL)")
    List<LocalDate> ListAllDayAbsenceInParticularMonthAndYear(Integer month, Integer year, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff);

    @Query("SELECT new com.timesheet.dto.absence.AbsenceManageViewDto(absence.id, CONCAT(absence.employee.firstName, ' ', absence.employee.lastName) , absence.employee.email, absence.employee.department.name, absence.typeTimeOff, absence.timeOff, absence.reason, absence.dateRequest, absence.status, absence.punishmentStatus) " +
            "FROM Absence absence WHERE " +
            "   (absence.dateRequest = ?1 OR ?1 IS NULL) " +
            "   AND (absence.employee.email LIKE %?2% OR ?2 IS NULL) " +
            "   AND (absence.status = ?3 OR ?3 IS NULL) " +
            "   AND (absence.typeTimeOff = ?4 OR ?4 IS NULL)")
    List<AbsenceManageViewDto> getAbsenceByDateAllEmployee(LocalDate localDate, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff);
}