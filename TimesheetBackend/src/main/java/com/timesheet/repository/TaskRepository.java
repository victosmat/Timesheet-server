package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Task;
import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.timesheet.dto.task.TaskSaveSto;
import com.timesheet.dto.task.TaskSelectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT new com.timesheet.dto.task.TaskSelectDto(task.id, CONCAT(task.name,': ',task.description)) FROM Task task WHERE task.project.id = ?1")
    List<TaskSelectDto> findAllByProjectId(Integer projectId);

    @Query("SELECT new com.timesheet.dto.task.TaskSaveSto(task.id, task.name, task.description, task.taskType, task.taskStatus, task.priorityType, task.project.id) " +
            "FROM Task task " +
            "WHERE task.project.id = ?1 " +
            "AND (LOWER(task.name) LIKE LOWER(CONCAT('%',?2,'%')) OR LOWER(task.description) LIKE LOWER(CONCAT('%',?2,'%')) OR ?2 IS NULL) " +
            "AND (task.taskType = ?3 OR ?3 IS NULL) " +
            "AND (task.taskStatus = ?4 OR ?4 IS NULL) " +
            "AND (task.priorityType = ?5 OR ?5 IS NULL)")
    List<TaskSaveSto> findAllTaskDetailByProjectId(Integer projectId, String keyword, TaskType taskType, TaskStatus taskStatus, PriorityType priorityType);
}
