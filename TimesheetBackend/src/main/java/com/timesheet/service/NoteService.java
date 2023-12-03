package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteComment.NoteCommentViewDto;
import com.timesheet.dto.employee.EmployeeViewNoteDto;
import com.timesheet.dto.note.*;
import com.timesheet.dto.project.ProjectOptionDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface NoteService {
    List<NotesPerDayDto> listTimesheetPerWeekNumber(Integer employeeId, Integer weekNumber);
    Note saveNote(NoteFormDto noteFormDto);
    void deleteNote(Integer noteId) throws NoteNotFoundException;
    void updateNewNotesStatusToPendingStatus(Integer currentWeekNumber);
    NoteFormDto getNoteFormById(Integer noteId);
    List<NoteSummaryDto> getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(NoteSummaryRequestDto noteSummaryRequestDto);
    Long getOpenTalkCount(CheckInRequestDto request);
    Map<TimeSheetStatus, List<NoteViewDto>> listAllPendingTimesheetOfStaffInParticularMonthAndYear(int staffId, int month, int year);
    void updatePendingTimesheetStatus(int timesheetId, TimeSheetStatus status);

    List<Note> listAllNoteByEmployee(Integer id);

    NoteCommentViewDto getNoteCommentById(Integer noteId);

    List<NoteDetailDto> listAllNote(TimeSheetStatus status, LocalDate startDate, LocalDate endDate, String emailKeyword, Integer pmId);

    List<NoteDetailDto> listAllNoteByStatus(TimeSheetStatus status, String emailKeyword, Integer pmId);
}
