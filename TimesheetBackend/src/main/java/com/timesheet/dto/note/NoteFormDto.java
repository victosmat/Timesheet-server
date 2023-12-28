package com.timesheet.dto.note;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;

import java.time.LocalDate;

public class NoteFormDto {
    private Integer id;
    private Integer employeeId;
    private Integer projectId;
    private Integer taskId;
    private String noteDescription;
    private Float workingTime;
    private WorkingType workingType;
    private LocalDate dateSubmit;
    private LocalDate dateModify;
    private TimeSheetStatus status;
    private String comment;
    private LocalDate createdDate;

    public NoteFormDto() {
    }
    public NoteFormDto(Integer id, Integer employeeId, Integer projectId, Integer taskId, String noteDescription, Float workingTime, WorkingType workingType, LocalDate dateSubmit, LocalDate dateModify, TimeSheetStatus status, String comment, LocalDate createdDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.taskId = taskId;
        this.noteDescription = noteDescription;
        this.workingTime = workingTime;
        this.workingType = workingType;
        this.dateSubmit = dateSubmit;
        this.dateModify = dateModify;
        this.status = status;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
