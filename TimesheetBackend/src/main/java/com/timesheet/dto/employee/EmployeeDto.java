package com.timesheet.dto.employee;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.note.NoteDetailViewDto;

import java.util.List;

public class EmployeeDto {
    private Integer employeeId;
    private String employeeName;
    private DepartmentLevelStatus employeeLevel;
    private String employeeDepartment;
    private List<NoteDetailViewDto> noteDetailViewDtos;

    public EmployeeDto(Integer employeeId, String employeeName, DepartmentLevelStatus employeeLevel, String employeeDepartment, List<NoteDetailViewDto> noteDetailViewDtos) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeLevel = employeeLevel;
        this.employeeDepartment = employeeDepartment;
        this.noteDetailViewDtos = noteDetailViewDtos;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public List<NoteDetailViewDto> getNoteDetailViewDtos() {
        return noteDetailViewDtos;
    }

    public void setNoteDetailViewDtos(List<NoteDetailViewDto> noteDetailViewDtos) {
        this.noteDetailViewDtos = noteDetailViewDtos;
    }
}
