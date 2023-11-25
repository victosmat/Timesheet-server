package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.timesheet.dto.task.TaskSaveSto;
import com.timesheet.dto.task.TaskSelectDto;

import java.util.List;

public interface TaskService {
    List<TaskSelectDto> listAllTaskForFormByProjectId(Integer projectId);

    TaskSaveSto saveTaskByProject(TaskSaveSto taskSaveSto);

    List<TaskSaveSto> listAllTaskDetailByProjectId(Integer projectId, String keyword, TaskType taskType, TaskStatus taskStatus, PriorityType priorityType);
}
