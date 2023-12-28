package com.timesheet.dto.note;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;

import java.time.LocalDate;

public class NoteViewDto {
    private Integer id;
    private String projectCode;
    private String taskName;
    private String noteDescription;
    private Float workingTime;
    private LocalDate dateSubmit;
    private LocalDate dateModify;
    private TimeSheetStatus status;
    private String comment;
    private Boolean isRead;

    public NoteViewDto() {
    }

    public NoteViewDto(Integer id, String projectCode, String taskName, String noteDescription, Float workingTime, LocalDate dateSubmit, LocalDate dateModify, TimeSheetStatus status, String comment, Boolean isRead) {
        this.id = id;
        this.projectCode = projectCode;
        this.taskName = taskName;
        this.noteDescription = noteDescription;
        this.workingTime = workingTime;
        this.dateSubmit = dateSubmit;
        this.dateModify = dateModify;
        this.status = status;
        this.comment = comment;
        this.isRead = isRead;
    }

    public Boolean isRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Float getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Float workingTime) {
        this.workingTime = workingTime;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public LocalDate getDateModify() {
        return dateModify;
    }

    public void setDateModify(LocalDate dateModify) {
        this.dateModify = dateModify;
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
