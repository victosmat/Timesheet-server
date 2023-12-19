package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.EmployeeProject;
import com.manage.employeemanagementmodel.entity.Note;
import com.manage.employeemanagementmodel.entity.Project;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.exception.NoteNotFoundException;
import com.timesheet.dto.NoteComment.NoteCommentViewDto;
import com.timesheet.dto.employee.EmployeeDto;
import com.timesheet.dto.employee.EmployeeViewNoteDto;
import com.timesheet.dto.note.*;
import com.timesheet.dto.mapper.NoteFormDtoMapper;
import com.timesheet.dto.project.ProjectOptionDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.dto.request_body.NoteSummaryRequestDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.repository.NoteRepository;
import com.timesheet.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteFormDtoMapper noteFormDtoMapper;
    private final EmployeeRepository employeeRepository;

    public NoteServiceImpl(NoteRepository noteRepository, NoteFormDtoMapper noteFormDtoMapper, EmployeeRepository employeeRepository) {
        this.noteRepository = noteRepository;
        this.noteFormDtoMapper = noteFormDtoMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<NotesPerDayDto> listTimesheetPerWeekNumber(Integer employeeId, Integer weekNumber) {
        List<NoteViewDto> noteViewDtos = noteRepository.listNotesByWeekNumber(employeeId, weekNumber);
        Map<LocalDate, List<NoteViewDto>> notesPerDate = noteViewDtos.stream().collect(Collectors.groupingBy(NoteViewDto::getDateSubmit));
        List<NotesPerDayDto> notesPerDayDtoList = new ArrayList<>();
        for (Map.Entry<LocalDate, List<NoteViewDto>> entry : notesPerDate.entrySet()) {
            notesPerDayDtoList.add(new NotesPerDayDto(entry.getKey(), entry.getValue()));
        }
        notesPerDayDtoList.sort(Comparator.comparing(NotesPerDayDto::getDateSubmit));
        return notesPerDayDtoList;
    }

    @Override
    public Note saveNote(NoteFormDto noteFormDto) {
        Note note = noteFormDtoMapper.noteFormDtoToNote(noteFormDto);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Integer noteId) throws NoteNotFoundException {
        boolean exist = noteRepository.existsById(noteId);
        if (!exist) {
            throw new NoteNotFoundException("Cannot find any note with the given information");
        } else {
            try {
                noteRepository.deleteById(noteId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateNewNotesStatusToPendingStatus(Integer currentWeekNumber) {
        try {
            noteRepository.pendingAllNewTimesheetRequest(currentWeekNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NoteFormDto getNoteFormById(Integer noteId) {
        return noteRepository.getNoteFormById(noteId);
    }

    @Override
    public List<NoteSummaryDto> getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(NoteSummaryRequestDto noteSummaryRequestDto) {
        return noteRepository.getTotalTimesheetHoursForEachDayInSpecificMonthAndYear(noteSummaryRequestDto);
    }

    @Override
    public Long getOpenTalkCount(CheckInRequestDto request) {
        return noteRepository.getOpenTalkCount(request);
    }

    @Override
    public Map<TimeSheetStatus, List<NoteViewDto>> listAllPendingTimesheetOfStaffInParticularMonthAndYear(int staffId, int month, int year) {
        List<NoteViewDto> noteViewDtoList = noteRepository.listAllPendingTimesheetOfStaffInParticularMonth(staffId, month, year);
        return noteViewDtoList.stream().collect(Collectors.groupingBy(NoteViewDto::getStatus, Collectors.toList()));
    }

    @Override
    public void updatePendingTimesheetStatus(List<Integer> timesheetId, TimeSheetStatus status) {
        timesheetId.forEach(id -> noteRepository.updatePendingTimesheetStatus(id, status));
    }

    @Override
    public List<Note> listAllNoteByEmployee(Integer id) {
        return noteRepository.findAllByEmployeeId(id);
    }

    @Override
    public NoteCommentViewDto getNoteCommentById(Integer noteId) {
        return noteRepository.getNoteCommentById(noteId);
    }

    @Override
    public List<NoteDetailDto> listAllNote(TimeSheetStatus status, LocalDate startDate, LocalDate endDate, String emailKeyword, Integer pmId) {
        List<NoteMapDto> noteMapDtos = noteRepository.listAllNoteByStatusAndDate(status, startDate, endDate, emailKeyword).stream().map(this::mapDto).toList();
        return getNoteDetailDtos(noteMapDtos, pmId);
    }

    private List<NoteDetailDto> getNoteDetailDtos(List<NoteMapDto> noteMapDtos, Integer pmId) {
        List<EmployeeProject> listProjectByPmId = Objects.requireNonNull(employeeRepository.findById(pmId).orElse(null)).getEmployeeProjects();
        List<Project> listProject = listProjectByPmId.stream().map(EmployeeProject::getProject).toList();

        Map<ProjectOptionDto, Map<EmployeeViewNoteDto, List<NoteMapDto>>> projectMap = new HashMap<>();
        noteMapDtos.forEach(noteMapDto -> {
            ProjectOptionDto projectDto = noteMapDto.getProjectOptionDto();

            for (Project project : listProject) {
                if (project.getId().equals(projectDto.getId())) {
                    EmployeeViewNoteDto employeeDto = noteMapDto.getEmployeeViewNoteDto();
                    if (!projectMap.containsKey(projectDto)) projectMap.put(projectDto, new HashMap<>());
                    Map<EmployeeViewNoteDto, List<NoteMapDto>> employeeNotes = projectMap.get(projectDto);
                    if (!employeeNotes.containsKey(employeeDto)) employeeNotes.put(employeeDto, new ArrayList<>());
                    employeeNotes.get(employeeDto).add(noteMapDto);
                    break;
                }
            }

        });
        return projectMap.entrySet().stream().map(entry -> {
            ProjectOptionDto projectDto = entry.getKey();
            Map<EmployeeViewNoteDto, List<NoteMapDto>> employeeNotes = entry.getValue();
            return new NoteDetailDto(
                    projectDto.getId(),
                    projectDto.getName(),
                    employeeNotes.entrySet().stream().map(employeeEntry -> {
                        EmployeeViewNoteDto employeeDto = employeeEntry.getKey();
                        List<NoteMapDto> notes = employeeEntry.getValue();
                        return new EmployeeDto(
                                employeeDto.getId(),
                                employeeDto.getEmployeeName(),
                                employeeDto.getEmployeeLevel(),
                                employeeDto.getEmployeeDepartment(),
                                notes.stream().map(NoteMapDto::getNoteDetailViewDto).toList()
                        );
                    }).toList()
            );
        }).toList();
    }

    public List<NoteDetailDto> listAllNoteByStatus(TimeSheetStatus status, String emailKeyword, Integer pmId) {
        List<NoteMapDto> noteMapDtos = noteRepository.listAllNoteByStatus(status, emailKeyword, pmId).stream().map(this::mapDto).toList();
        return getNoteDetailDtos(noteMapDtos, pmId);
    }


    public NoteMapDto mapDto(NoteDto noteDto) {
        return new NoteMapDto(
                noteDto.getNoteId(),
                new ProjectOptionDto(
                        noteDto.getProjectId(),
                        noteDto.getProjectDes()
                ),
                new EmployeeViewNoteDto(
                        noteDto.getEmployeeId(),
                        noteDto.getEmployeeName(),
                        noteDto.getEmployeeLevel(),
                        noteDto.getEmployeeDepartment()
                ),
                noteDto.getNote(),
                noteDto.getDateSubmit(),
                noteDto.getDateModify(),
                noteDto.getWorkingTime(),
                noteDto.getTaskCode(),
                noteDto.getTaskStatus(),
                noteDto.getWorkingType(),
                noteDto.getStatus(),
                noteDto.getNoteCommentId(),
                noteDto.getComment(),
                noteDto.getReaded()
        );
    }
}
