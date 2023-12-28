package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Task;
import com.manage.employeemanagementmodel.entity.enums.PriorityType;
import com.manage.employeemanagementmodel.entity.enums.TaskStatus;
import com.manage.employeemanagementmodel.entity.enums.TaskType;
import com.timesheet.dto.task.TaskSaveSto;
import com.timesheet.dto.task.TaskSelectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT t.id, CONCAT(t.code, ': ', t.description) AS des\n" +
            "FROM task t\n" +
            "WHERE t.project_id = 10 AND t.task_status != 'DONE'", nativeQuery = true)
    List<TaskSelectDto> findAllByProjectId(Integer projectId);

    @Query("SELECT new com.timesheet.dto.task.TaskSaveSto(task.id, task.name, task.description, task.taskType, task.taskStatus, task.priorityType, task.project.id) " +
            "FROM Task task " +
            "WHERE task.project.id = ?1 " +
            "AND (LOWER(task.name) LIKE LOWER(CONCAT('%',?2,'%')) OR LOWER(task.description) LIKE LOWER(CONCAT('%',?2,'%')) OR ?2 IS NULL) " +
            "AND (task.taskType = ?3 OR ?3 IS NULL) " +
            "AND (task.taskStatus = ?4 OR ?4 IS NULL) " +
            "AND (task.priorityType = ?5 OR ?5 IS NULL)")
    Page<TaskSaveSto> findAllTaskDetailByProjectId(Integer projectId, String keyword, TaskType taskType, TaskStatus taskStatus, PriorityType priorityType, Pageable pageable);
}
