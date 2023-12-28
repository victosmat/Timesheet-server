package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.timesheet.dto.NoteComment.NoteCommentViewDto;
import com.timesheet.dto.employee.EmployeeDto;
import com.timesheet.dto.note.*;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("SELECT note FROM Note note WHERE note.employee.id = :id")
    List<Note> findAllByEmployeeId(@Param("id") Integer id);

    @Query("SELECT new com.timesheet.dto.note.NoteViewDto(note.id, note.task.project.name, note.task.name, note.note, note.workingTime, note.dateSubmit, note.dateModify, note.status, COALESCE(nc.comment, NULL), nc.isReaded) " +
            "FROM Note note " +
            "LEFT JOIN NoteComment nc ON nc.note.id = note.id " +
            "WHERE WEEK(note.dateSubmit) = :weekNumber AND note.employee.id = :employeeId ORDER BY note.dateSubmit ASC")
    List<NoteViewDto> listNotesByWeekNumber(@Param("employeeId") Integer employeeId, @Param("weekNumber") int weekNumber);

    @Query("SELECT note FROM Note note WHERE MONTH(note.dateSubmit) = :month AND note.employee.id = :employeeId")
    List<Note> listNotesOfEmployeeByMonth(@Param("employeeId") Integer employeeId, @Param("month") int month);

    @Modifying
    @Query("UPDATE Note note SET note.status = com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus.PENDING " +
            "WHERE WEEK(note.dateSubmit) BETWEEN (?1 - 1) AND (?1) AND note.status = com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus.NEW")
    void pendingAllNewTimesheetRequest(Integer currentWeekNumber);

    @Query("SELECT new com.timesheet.dto.note.NoteFormDto(note.id, note.employee.id, note.task.project.id, note.task.id, note.note, note.workingTime, note.workingType, note.dateSubmit, note.dateModify, note.status, nc.comment, note.dateSubmit) " +
            "FROM Note note " +
            "LEFT JOIN NoteComment nc ON nc.note.id = note.id " +
            "WHERE note.id = ?1")
    NoteFormDto getNoteFormById(Integer id);

    @Query("SELECT new com.timesheet.dto.note.NoteSummaryDto(note.dateSubmit, SUM(note.workingTime)) FROM Note note WHERE MONTH(note.dateSubmit) = :#{#request.month} AND YEAR(note.dateSubmit) = :#{#request.year} AND note.employee.id = :#{#request.employeeId}" +
            " AND note.status IN :#{#request.statuses} GROUP BY note.dateSubmit")
    List<NoteSummaryDto> getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(NoteSummaryRequestDto request);

    @Query("SELECT count(note) FROM Note note WHERE MONTH(note.dateSubmit) = :#{#request.month} AND YEAR(note.dateSubmit) = :#{#request.year} AND note.employee.id = :#{#request.employeeId} AND note.task.id = 7")
    Long getOpenTalkCount(CheckInRequestDto request);

    @Query("SELECT new com.timesheet.dto.note.NoteViewDto(note.id, note.task.project.name" +
            ", note.task.name, note.note, note.workingTime, note.dateSubmit,note.dateModify, note.status, nc.comment, nc.isReaded) " +
            "FROM Note note " +
            "LEFT JOIN NoteComment nc ON nc.note.id = note.id " +
            "WHERE MONTH(note.dateSubmit) = ?2 AND YEAR(note.dateSubmit) = ?3 AND note.employee.id = ?1 ORDER BY note.dateSubmit ASC")
    List<NoteViewDto> listAllPendingTimesheetOfStaffInParticularMonth(int staffId, int month, int year);

    @Modifying
    @Query("UPDATE Note note SET note.status = ?2 " +
            "WHERE note.id = ?1")
    void updatePendingTimesheetStatus(int timesheetId, TimeSheetStatus status);

    @Query("SELECT new com.timesheet.dto.NoteComment.NoteCommentViewDto(nc.id, nc.comment, CONCAT(nc.employee.firstName, ' ',nc.employee.lastName)) " +
            "FROM NoteComment nc " +
            "LEFT JOIN Note n ON nc.note.id = n.id " +
            "WHERE n.id = ?1")
    NoteCommentViewDto getNoteCommentById(Integer noteId);

    @Query("SELECT new com.timesheet.dto.note.NoteDto(" +
            "note.id," +
            "note.employee.id, " +
            "note.task.project.id, " +
            "note.note, " +
            "note.dateSubmit, " +
            "note.dateModify, " +
            "note.workingTime, " +
            "CONCAT(note.task.project.code,': ',note.task.project.name), " +
            "CONCAT(note.task.name,': ',note.task.description), " +
            "note.task.taskStatus, " +
            "note.workingType, " +
            "note.status, " +
            "CONCAT(note.employee.firstName,' ',note.employee.lastName), " +
            "note.employee.employeeLevelStatus, " +
            "note.employee.department.name," +
            "nc.id," +
            "nc.comment," +
            "nc.isReaded) " +
            "FROM Note note " +
            "LEFT JOIN NoteComment nc ON nc.note.id = note.id " +
            "WHERE (note.status = ?1 OR ?1 IS NULL) " +
            "AND (note.dateSubmit BETWEEN ?2 AND ?3 OR ?2 IS NULL OR ?3 IS NULL) " +
            "AND note.employee.email LIKE %?4%")
    List<NoteDto> listAllNoteByStatusAndDate(TimeSheetStatus status, LocalDate startDate, LocalDate endDate, String emailKeyword);

    @Query("SELECT new com.timesheet.dto.note.NoteDto(" +
            "note.id," +
            "note.employee.id, " +
            "note.task.project.id, " +
            "note.note, " +
            "note.dateSubmit, " +
            "note.dateModify, " +
            "note.workingTime, " +
            "CONCAT(note.task.project.code,': ',note.task.project.name), " +
            "CONCAT(note.task.name,': ',note.task.description), " +
            "note.task.taskStatus, " +
            "note.workingType, " +
            "note.status, " +
            "CONCAT(note.employee.firstName,' ',note.employee.lastName), " +
            "note.employee.employeeLevelStatus, " +
            "note.employee.department.name," +
            "nc.id," +
            "nc.comment," +
            "nc.isReaded) " +
            "FROM Note note " +
            "LEFT JOIN NoteComment nc ON nc.note.id = note.id " +
            "WHERE (note.status = ?1 OR ?1 IS NULL) " +
            "AND note.employee.email LIKE %?2%")
    List<NoteDto> listAllNoteByStatus(TimeSheetStatus status, String emailKeyword, Integer pmId);
}


