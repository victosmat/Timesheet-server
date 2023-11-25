package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.timesheet.configuration.security.jwt.JwtTokenUtil;
import com.timesheet.dto.NoteComment.NoteCommentViewDto;
import com.timesheet.dto.note.NoteDetailDto;
import com.timesheet.dto.note.NoteDto;
import com.timesheet.dto.note.NoteFormDto;
import com.timesheet.dto.note.NotesPerDayDto;
import com.timesheet.dto.project.ProjectSelectDto;
import com.timesheet.dto.task.TaskSelectDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;
import com.timesheet.service.NoteService;
import com.timesheet.service.PermissionService;
import com.timesheet.service.ProjectService;
import com.timesheet.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("app/notes")
@SecurityRequirement(name = "bearer-key")
public class NoteRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(NoteRestController.class);
    private final NoteService noteService;
    private final PermissionService permissionService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final JwtTokenUtil jwtTokenUtil;

    public NoteRestController(NoteService noteService, PermissionService permissionService, ProjectService projectService, TaskService taskService, JwtTokenUtil jwtTokenUtil) {
        this.noteService = noteService;
        this.permissionService = permissionService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //    @PreAuthorize("hasAnyAuthority(@permissionService.getApiPermission(#request.getRequestURL().toString()))")
    @GetMapping("notes_by_week")
    public ResponseEntity<List<NotesPerDayDto>> getNotesListByWeekNumber(@RequestParam("employeeId") Integer employeeId,
                                                                         @RequestParam("weekNumber") Integer weekNumber) {
        return ResponseEntity.ok().body(noteService.listTimesheetPerWeekNumber(employeeId, weekNumber));
    }

    @GetMapping("get_all_note")
    public ResponseEntity<List<NoteDetailDto>> getAllNote(@RequestParam(required = false) TimeSheetStatus status,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                          @RequestParam(required = false) String emailKeyword) {
        if (startDate == null && endDate == null) {
            return ResponseEntity.ok().body(noteService.listAllNoteByStatus(status, emailKeyword));
        }
        assert startDate != null;
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ResponseEntity.ok().body(noteService.listAllNote(status, localStartDate, localEndDate, emailKeyword));
    }


    @PostMapping("save")
    public ResponseEntity<?> saveNote(@RequestBody NoteFormDto noteFormDto) {
        try {
            noteService.saveNote(noteFormDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("delete")
    public ResponseEntity<?> deleteNoteById(@RequestParam("noteId") Integer noteId) {
        try {
            noteService.deleteNote(noteId);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("submit_week_for_approved")
    public ResponseEntity<?> submitTimesheetWeek(@RequestParam("currentWeekNumber") Integer currentWeekNumber) {
        try {
            noteService.updateNewNotesStatusToPendingStatus(currentWeekNumber);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("project_for_form")
    public ResponseEntity<?> getProjectForForm(@RequestParam("employeeId") Integer employeeId) {
        try {
            List<ProjectSelectDto> lst = projectService.getAllProjectsForFormByEmployeeId(employeeId);
            return ResponseEntity.ok(lst);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("task_for_form")
    public ResponseEntity<List<TaskSelectDto>> getTaskForForm(@RequestParam("projectId") Integer projectId) {
        try {
            List<TaskSelectDto> lst = taskService.listAllTaskForFormByProjectId(projectId);
            return ResponseEntity.ok(lst);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("note_by_id")
    public ResponseEntity<?> getNoteById(@RequestParam("noteId") Integer noteId) {
        try {
            NoteFormDto note = noteService.getNoteFormById(noteId);
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("note_summary")
    public ResponseEntity<?> getNoteSummary(@RequestBody NoteSummaryRequestDto noteSummaryRequest) {
        try {
            return ResponseEntity.ok().body(noteService.getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(noteSummaryRequest));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("open_talk_count")
    public ResponseEntity<?> getOpenTalkCount(@RequestBody CheckInRequestDto request) {
        try {
            return ResponseEntity.ok().body(noteService.getOpenTalkCount(request));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("staff_timesheet_by_month")
    public ResponseEntity<?> getStaffTimesheetByMonthAndYear(@RequestParam("staffId") Integer staffId,
                                                             @RequestParam("month") Integer month,
                                                             @RequestParam("year") Integer year) {
        try {
            return ResponseEntity.ok().body(noteService.listAllPendingTimesheetOfStaffInParticularMonthAndYear(staffId, month, year));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    @PutMapping("update_staff_timesheet_status")
    public ResponseEntity<?> updateTimesheetStatus(@RequestParam("noteId") Integer noteId, @RequestParam("status") TimeSheetStatus status) {
        try {
            noteService.updatePendingTimesheetStatus(noteId, status);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("note_comment_by_id")
    public ResponseEntity<NoteCommentViewDto> getNoteCommentById(@RequestParam("noteId") Integer noteId) {
        try {
            return ResponseEntity.ok().body(noteService.getNoteCommentById(noteId));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }
}