package com.timesheet.dto.note;

import com.timesheet.dto.employee.EmployeeDto;

import java.util.List;

public class NoteDetailDto {
    private Integer projectId;
    private String projectDes;
    private List<EmployeeDto> employeeDtoList;

    public NoteDetailDto() {
    }

    public NoteDetailDto(Integer projectId, String projectDes, List<EmployeeDto> employeeDtoList) {
        this.projectId = projectId;
        this.projectDes = projectDes;
        this.employeeDtoList = employeeDtoList;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectDes() {
        return projectDes;
    }

    public void setProjectDes(String projectDes) {
        this.projectDes = projectDes;
    }

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}
