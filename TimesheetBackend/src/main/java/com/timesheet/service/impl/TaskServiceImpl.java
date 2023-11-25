package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Task;
import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.timesheet.dto.task.TaskSaveSto;
import com.timesheet.dto.task.TaskSelectDto;
import com.timesheet.repository.ProjectRepository;
import com.timesheet.repository.TaskRepository;
import com.timesheet.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TaskSelectDto> listAllTaskForFormByProjectId(Integer projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public TaskSaveSto saveTaskByProject(TaskSaveSto taskSaveSto) {
        if (taskSaveSto.getId() == null) {
            Task task = new Task();
            saveTask(taskSaveSto, task);
            taskSaveSto.setId(taskRepository.save(task).getId());
            return taskSaveSto;
        }
        Task task = taskRepository.findById(taskSaveSto.getId()).orElse(null);
        assert task != null;
        saveTask(taskSaveSto, task);
        taskRepository.save(task);
        return taskSaveSto;
    }

    @Override
    public List<TaskSaveSto>    listAllTaskDetailByProjectId(Integer projectId, String keyword, TaskType taskType, TaskStatus taskStatus, PriorityType priorityType) {
        return taskRepository.findAllTaskDetailByProjectId(projectId, keyword, taskType, taskStatus, priorityType);
    }

    private void saveTask(TaskSaveSto taskSaveSto, Task task) {
        task.setName(taskSaveSto.getName());
        task.setDescription(taskSaveSto.getDescription());
        task.setTaskType(taskSaveSto.getTaskType());
        task.setTaskStatus(taskSaveSto.getTaskStatus());
        task.setPriorityType(taskSaveSto.getPriorityType());
        task.setProject(projectRepository.findById(taskSaveSto.getProjectId()).orElse(null));
    }
}
