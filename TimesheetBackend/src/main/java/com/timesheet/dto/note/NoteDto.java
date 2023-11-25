package com.timesheet.dto.note;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;
import com.timesheet.dto.employee.EmployeeDto;

import java.time.LocalDate;

public class NoteDto {
    private Integer noteId;
    private Integer employeeId;
    private Integer projectId;
    private String note;
    private LocalDate dateSubmit;
    private LocalDate dateModify;
    private Integer workingTime;
    private String projectDes;
    private String taskDes;
    private WorkingType workingType;
    private TimeSheetStatus status;
    private String employeeName;
    private DepartmentLevelStatus employeeLevel;
    private String employeeDepartment;
    private Integer noteCommentId;
    private String comment;
    private Boolean isReaded;

    public NoteDto(Integer noteId, Integer employeeId, Integer projectId, String note, LocalDate dateSubmit, LocalDate dateModify, Integer workingTime, String projectDes, String taskDes, WorkingType workingType, TimeSheetStatus status, String employeeName, DepartmentLevelStatus employeeLevel, String employeeDepartment, Integer noteCommentId, String comment, Boolean isReaded) {
        this.noteId = noteId;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.note = note;
        this.dateSubmit = dateSubmit;
        this.dateModify = dateModify;
        this.workingTime = workingTime;
        this.projectDes = projectDes;
        this.taskDes = taskDes;
        this.workingType = workingType;
        this.status = status;
        this.employeeName = employeeName;
        this.employeeLevel = employeeLevel;
        this.employeeDepartment = employeeDepartment;
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

    public String getProjectDes() {
        return projectDes;
    }

    public void setProjectDes(String projectDes) {
        this.projectDes = projectDes;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public DepartmentLevelStatus getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(DepartmentLevelStatus employeeLevel) {
        this.employeeLevel = employeeLevel;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
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
