package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.timesheet.dto.task.TaskSaveSto;
import com.timesheet.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<TaskSaveSto>> getTaskDetailByProject(@RequestParam(name = "pageNum") Integer pageNum,
                                                                    @RequestParam(name = "pageSize") Integer pageSize,
                                                                    @RequestParam(name = "sortField") String sortField,
                                                                    @RequestParam(name = "sortDir") String sortDir,
                                                                    @RequestParam(name = "projectId") Integer projectId,
                                                                    @RequestParam(value = "keyword", required = false) String keyword,
                                                                    @RequestParam(value = "Type", required = false) TaskType taskType,
                                                                    @RequestParam(value = "status", required = false) TaskStatus taskStatus,
                                                                    @RequestParam(value = "priority", required = false) PriorityType priorityType) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return ResponseEntity.ok(taskService.listAllTaskDetailByProjectId(projectId, keyword, taskType, taskStatus, priorityType, pageable));
    }
}
