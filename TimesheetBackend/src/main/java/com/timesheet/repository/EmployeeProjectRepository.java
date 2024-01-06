package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.EmployeeProject;
import com.timesheet.dto.project.EmployeeProjectDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer> {

    @Query("SELECT new com.timesheet.dto.project.EmployeeProjectDetailDto(" +
            "ep.id, ep.employee.id, CONCAT(ep.employee.firstName,' ',ep.employee.lastName), ep.employee.email , ep.employee.employeeLevelStatus, ep.roleProjectType) " +
            "FROM EmployeeProject ep " +
            "WHERE ep.project.id = ?1")
    List<EmployeeProjectDetailDto> findAllEmployeeProjectDetailsByProjectId(Integer projectId);
}
