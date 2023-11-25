package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.timesheet.dto.project.*;

import java.util.List;

public interface ProjectService {
    List<ProjectSelectDto> getAllProjectsForFormByEmployeeId(Integer employeeId);

    List<ProjectViewDto> getAllProjectsForForm(String name);

    List<ProjectViewDto> getAllProjectsForFormByStatus(ProjectStatus status, String name);

    ProjectDetailViewDto getProjectDetails(Integer projectId);

    ProjectSaveDto saveProject(ProjectSaveDto projectSaveDto);

    Boolean deleteProject(Integer projectId);
}
