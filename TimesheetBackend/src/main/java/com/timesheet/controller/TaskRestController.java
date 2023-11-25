package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.timesheet.dto.task.TaskSaveSto;
import com.timesheet.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/tasks")
@SecurityRequirement(name = "bearer-key")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/save_by_project")
    public ResponseEntity<TaskSaveSto> saveTaskByProject(@RequestBody TaskSaveSto taskSaveSto) {
        return ResponseEntity.ok(taskService.saveTaskByProject(taskSaveSto));
    }

    @GetMapping("/get_task_detail_by_project")
    public ResponseEntity<List<TaskSaveSto>> getTaskDetailByProject(@RequestParam("projectId") Integer projectId,
                                                                    @RequestParam(value = "keyword", required = false) String keyword,
                                                                    @RequestParam(value = "Type", required = false) TaskType taskType,
                                                                    @RequestParam(value = "status", required = false) TaskStatus taskStatus,
                                                                    @RequestParam(value = "priority", required = false) PriorityType priorityType) {
        return ResponseEntity.ok(taskService.listAllTaskDetailByProjectId(projectId, keyword, taskType, taskStatus, priorityType));
    }
}
