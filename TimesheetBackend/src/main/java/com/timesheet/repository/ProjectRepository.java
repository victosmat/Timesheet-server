package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Project;
import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.timesheet.dto.project.ProjectDetailDto;
import com.timesheet.dto.project.ProjectSaveDto;
import com.timesheet.dto.project.ProjectSelectDto;
import com.timesheet.dto.project.ProjectViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT new com.timesheet.dto.project.ProjectSelectDto(project.id, project.code, project.name) FROM EmployeeProject employeeProject" +
            " JOIN Project project ON employeeProject.project.id = project.id" +
            " WHERE employeeProject.employee.id = ?1 AND employeeProject.isTemp = true")
    List<ProjectSelectDto> findAllProjectForForm(Integer employeeId);

    @Query("SELECT new com.timesheet.dto.project.ProjectViewDto(project.id, project.code, project.name, project.description, project.startDate, project.endDate, " +
            "(" +
            "SELECT CONCAT(employee.firstName,' ', employee.lastName) FROM Employee employee " +
            "   JOIN EmployeeProject ep ON ep.employee.id = employee.id " +
            "   WHERE ep.project.id = project.id AND ep.roleProjectType = 'PM'" +
            "), " +
            "(" +
            "SELECT COUNT(ep) FROM EmployeeProject ep WHERE ep.project.id = project.id), " +
            "project.projectStatus" +
            ") " +
            "FROM Project project " +
            "WHERE project.name LIKE %?1%")
    Page<ProjectViewDto> findAllProjectForFormByName(String name, Pageable pageable);

    @Query("SELECT new com.timesheet.dto.project.ProjectViewDto(project.id, project.code, project.name, project.description, project.startDate, project.endDate, " +
            "(" +
            "SELECT CONCAT(employee.firstName,' ', employee.lastName) FROM Employee employee " +
            "   JOIN EmployeeProject ep ON ep.employee.id = employee.id " +
            "   WHERE ep.project.id = project.id AND ep.roleProjectType = 'PM'" +
            "), " +
            "(" +
            "SELECT COUNT(ep) FROM EmployeeProject ep WHERE ep.project.id = project.id), " +
            "project.projectStatus" +
            ") " +
            "FROM Project project " +
            "WHERE project.projectStatus = ?1 AND project.name LIKE %?2%")
    Page<ProjectViewDto> findAllProjectForFormByStatusAndName(ProjectStatus status, String name, Pageable pageable);


    @Query("SELECT new com.timesheet.dto.project.ProjectDetailDto(" +
            "project.id, project.code, project.name, project.description, project.projectType, project.projectStatus, project.startDate, project.endDate) " +
            "FROM Project project " +
            "WHERE project.id = ?1")
    ProjectDetailDto getProjectDetails(Integer projectId);
}
