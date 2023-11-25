package com.timesheet.dto.note;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;

import java.time.LocalDate;

public class NoteViewDto {
    private Integer id;
    private String projectCode;
    private String taskName;
    private String noteDescription;
    private Integer workingTime;
    private LocalDate dateSubmit;
    private TimeSheetStatus status;
    private String comment;

    public NoteViewDto() {
    }

    public NoteViewDto(Integer id, String projectCode, String taskName, String noteDescription, Integer workingTime, LocalDate dateSubmit, TimeSheetStatus status, String comment) {
        this.id = id;
        this.projectCode = projectCode;
        this.taskName = taskName;
        this.noteDescription = noteDescription;
        this.workingTime = workingTime;
        this.dateSubmit = dateSubmit;
        this.status = status;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public TimeSheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSheetStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
