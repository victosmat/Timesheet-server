package com.timesheet.dto.note;

import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;

import java.time.LocalDate;

public class NoteDetailViewDto {
    private Integer noteId;
    private String note;
    private LocalDate dateSubmit;
    private LocalDate dateModify;
    private Integer workingTime;
    private String taskDes;
    private TaskStatus taskStatus;
    private WorkingType workingType;
    private TimeSheetStatus status;
    private Integer noteCommentId;
    private String comment;
    private Boolean isReaded;

    public NoteDetailViewDto(Integer noteId, String note, LocalDate dateSubmit, LocalDate dateModify, Integer workingTime, String taskDes, TaskStatus taskStatus, WorkingType workingType, TimeSheetStatus status, Integer noteCommentId, String comment, Boolean isReaded) {
        this.noteId = noteId;
        this.note = note;
        this.dateSubmit = dateSubmit;
        this.dateModify = dateModify;
        this.workingTime = workingTime;
        this.taskDes = taskDes;
        this.taskStatus = taskStatus;
        this.workingType = workingType;
        this.status = status;
        this.noteCommentId = noteCommentId;
        this.comment = comment;
        this.isReaded = isReaded;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public String getTaskCode() {
        return taskDes;
    }

    public void setTaskCode(String taskCode) {
        this.taskDes = taskCode;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    public TimeSheetStatus getStatus() {
        return status;
    }

    public void setStatus(TimeSheetStatus status) {
        this.status = status;
    }

    public Integer getNoteCommentId() {
        return noteCommentId;
    }

    public void setNoteCommentId(Integer noteCommentId) {
        this.noteCommentId = noteCommentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getReaded() {
        return isReaded;
    }

    public void setReaded(Boolean readed) {
        isReaded = readed;
    }
}
