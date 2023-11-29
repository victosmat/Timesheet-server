package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.timesheet.dto.project.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    List<ProjectSelectDto> getAllProjectsForFormByEmployeeId(Integer employeeId);

    Page<ProjectViewDto> getAllProjectsForForm(String name, Pageable pageable);

    Page<ProjectViewDto> getAllProjectsForFormByStatus(ProjectStatus status, String name, Pageable pageable);

    ProjectDetailViewDto getProjectDetails(Integer projectId);

    ProjectSaveDto saveProject(ProjectSaveDto projectSaveDto);

    Boolean deleteProject(Integer projectId);
}
