package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.checkin.CheckinPunishmentDto;
import com.timesheet.dto.checkin.ICheckInManageDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    @Query("SELECT new com.timesheet.dto.CheckInDto(checkIn.id, checkIn.checkInTime, checkIn.checkOutTime, checkIn.status, checkIn.employee.id) " +
            "FROM CheckIn checkIn WHERE MONTH(checkIn.checkInTime) = :#{#request.month} AND YEAR(checkIn.checkInTime) = :#{#request.year} AND checkIn.employee.id = :#{#request.employeeId} " +
            "AND checkIn.status = :#{#request.status}")
    List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request);

    @Query("SELECT new com.timesheet.dto.CheckInDto(checkIn.id, checkIn.checkInTime, checkIn.checkOutTime, checkIn.status, checkIn.employee.id) " +
            "FROM CheckIn checkIn WHERE DATE(checkIn.currentDate) = DATE(?1) AND checkIn.employee.id = ?2")
    CheckInDto getCheckInByLocalDateTime(LocalDateTime checkPointTime, Integer employeeId);

    @Query("SELECT checkIn.checkInTime FROM CheckIn checkIn WHERE checkIn.employee.id = ?1 AND DATE(checkIn.checkInTime) = DATE(?2)")
    LocalDate getCheckinByEmployeeIdAndDate(Integer employeeId, LocalDate localDate);

    @Query("SELECT checkIn.checkOutTime FROM CheckIn checkIn WHERE checkIn.employee.id = ?1 AND DATE(checkIn.checkOutTime) = DATE(?2)")
    LocalDate getCheckoutByEmployeeIdAndDate(Integer employeeId, LocalDate localDate);

    @Query("SELECT new com.timesheet.dto.CheckInDto(checkIn.id, checkIn.checkInTime, checkIn.checkOutTime, checkIn.status, checkIn.employee.id) " +
            "FROM CheckIn checkIn WHERE DATE(checkIn.currentDate) = ?2 AND checkIn.employee.id = ?1")
    CheckInDto getCheckInOfEmployeePerDay(Integer employeeId, LocalDate now);


    @Query("SELECT new com.timesheet.dto.checkin.CheckinPunishmentDto(checkIn.id, checkIn.currentDate, checkIn.checkInTime, checkIn.checkOutTime, checkIn.status, pt.description, pt.punishmentMoney, punishment.complain, punishment.complainReply, punishment.isDeleted) " +
            "FROM CheckIn checkIn " +
            "LEFT JOIN Punishment punishment ON checkIn.id = punishment.checkIn.id " +
            "LEFT JOIN PunishmentType pt ON punishment.punishmentType.id = pt.id " +
            "WHERE (checkIn.employee.id = ?1 OR ?1 IS NULL) " +
            "AND (checkIn.status = ?2 OR ?2 IS NULL) " +
            "AND (MONTH(checkIn.currentDate) = ?3 OR ?3 IS NULL) " +
            "AND (YEAR(checkIn.currentDate) = ?4 OR ?4 IS NULL) " +
            "AND ((?5 = true AND pt.description IS NOT NULL) OR (?5 = false AND pt.description IS NULL) OR (?5 = null)) " +
            "AND ((?6 = true AND punishment.complain IS NOT NULL) OR (?6 = false AND punishment.complain IS NULL) OR (?6 = null)) ")
    Page<CheckinPunishmentDto> getCheckinOfEmployeeAndPunishmentByStatus(Integer employeeId, CheckInStatus status, int month, int year, Boolean isManage, Boolean isComplain, Pageable pageable);


    @Query("SELECT count(checkIn) FROM CheckIn checkIn WHERE checkIn.currentDate = ?1")
    int checkCurrentDateIsExist(LocalDateTime localDateTime);

    @Query("SELECT new com.timesheet.dto.checkin.CheckinPunishmentDto(checkIn.id, checkIn.currentDate, checkIn.checkInTime, checkIn.checkOutTime,checkIn.status, pt.description, pt.punishmentMoney, punishment.complain,  punishment.complainReply, punishment.isDeleted) " +
            "FROM CheckIn checkIn " +
            "LEFT JOIN Punishment punishment ON checkIn.id = punishment.checkIn.id " +
            "LEFT JOIN PunishmentType pt ON punishment.punishmentType.id = pt.id " +
            "WHERE checkIn.employee.id = ?1 AND MONTH (checkIn.currentDate) = ?2 AND YEAR (checkIn.currentDate) = ?3 ")
    List<CheckinPunishmentDto> getCheckinOfEmployeeAndPunishment(Integer employeeId, int month, int year);

    @Query(value = "SELECT e.id                                                   AS id,\n" +
            "       CONCAT(e.first_name, ' ', e.last_name)                 AS fullName,\n" +
            "       e.email                                                AS email,\n" +
            "       d.name                                                 AS departmentName,\n" +
            "       SUM(IF(pt.description LIKE '%check in muộn%', 1, 0))   AS countCheckInLate,\n" +
            "       SUM(IF(pt.description LIKE '%không check out%', 1, 0)) AS countNotCheckOut,\n" +
            "       SUM(IF(pt.description LIKE '%không check in%', 1, 0))  AS countNotCheckIn\n" +
            "FROM employee e\n" +
            "         LEFT JOIN\n" +
            "     department d ON e.department_id = d.id\n" +
            "         LEFT JOIN\n" +
            "     check_in c ON c.employee_id = e.id\n" +
            "         LEFT JOIN\n" +
            "     punishments p ON p.check_in_id = c.id\n" +
            "         LEFT JOIN\n" +
            "     punishment_type pt ON pt.id = p.punishment_type_id\n" +
            "WHERE (LOWER(e.first_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(e.last_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(e.email) LIKE LOWER(CONCAT('%', ?1, '%')))\n" +
            "  AND MONTH(c.current_date_checkin) = ?2\n" +
            "  AND YEAR(c.current_date_checkin) = ?3\n" +
            "  AND d.name LIKE CONCAT('%', ?4, '%')\n" +
            "GROUP BY e.id, e.first_name, e.last_name, e.email, d.name\n" +
            "ORDER BY e.id\n", nativeQuery = true)
    Page<ICheckInManageDto> getAllCheckinAndPunishment(String keyword, Integer month, Integer year, String department, Pageable pageable);
}
