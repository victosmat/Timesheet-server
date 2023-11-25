package com.timesheet.dto.task;

public class TaskSelectDto {
    private Integer id;
    private String des;

    public TaskSelectDto() {
    }

    public TaskSelectDto(Integer id, String des) {
        this.id = id;
        this.des = des;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String name) {
        this.des = name;
    }
}
