package com.timesheet.dto.department;

import jakarta.persistence.Column;

public class DepartmentDto {
    private Integer id;
    private String name;

    public DepartmentDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
