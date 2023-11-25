package com.timesheet.dto.task;

import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;


public class TaskSaveSto {
    private Integer id;
    private String name;
    private String description;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private PriorityType priorityType;
    private Integer projectId;

    public TaskSaveSto(Integer id, String name, String description, TaskType taskType, TaskStatus taskStatus, PriorityType priorityType, Integer projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.priorityType = priorityType;
        this.projectId = projectId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
