package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.EmployeeProject;
import com.manage.employeemanagementmodel.entity.Project;
import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.timesheet.dto.project.*;
import com.timesheet.repository.EmployeeProjectRepository;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.repository.ProjectRepository;
import com.timesheet.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeProjectRepository employeeProjectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeProjectRepository employeeProjectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeProjectRepository = employeeProjectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<ProjectSelectDto> getAllProjectsForFormByEmployeeId(Integer employeeId) {
        return projectRepository.findAllProjectForForm(employeeId);
    }

    @Override
    public List<ProjectViewDto> getAllProjectsForForm(String name) {
        return projectRepository.findAllProjectForFormByName(name);
    }

    @Override
    public List<ProjectViewDto> getAllProjectsForFormByStatus(ProjectStatus status, String name) {
        return projectRepository.findAllProjectForFormByStatusAndName(status, name);
    }

    @Override
    public ProjectDetailViewDto getProjectDetails(Integer projectId) {
        ProjectDetailDto projectDetailDto = projectRepository.getProjectDetails(projectId);
        List<EmployeeProjectDetailDto> employeeProjectDetailDtos = employeeProjectRepository.findAllEmployeeProjectDetailsByProjectId(projectId);
        projectDetailDto.setProjectEmployeeSaveDtos(employeeProjectDetailDtos);
        return new ProjectDetailViewDto(
                projectDetailDto.getId(),
                projectDetailDto.getCode(),
                projectDetailDto.getName(),
                projectDetailDto.getDescription(),
                projectDetailDto.getProjectType(),
                projectDetailDto.getProjectStatus(),
                projectDetailDto.getStartDate().toString(),
                projectDetailDto.getEndDate().toString(),
                employeeProjectDetailDtos
        );
    }

    @Override
    public ProjectSaveDto saveProject(ProjectSaveDto projectSaveDto) {
        Project project;
        if (projectSaveDto.getId() == null) {
            project = new Project();
            project = getProject(projectSaveDto, project);
            projectSaveDto.setId(project.getId());

        } else {
            project = projectRepository.findById(projectSaveDto.getId()).orElse(null);
            assert project != null;
            project = getProject(projectSaveDto, project);
        }
        return getProjectSaveDto(projectSaveDto, project);
    }

    @Override
    public Boolean deleteProject(Integer projectId) {
        try {
            projectRepository.deleteById(projectId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Project getProject(ProjectSaveDto projectSaveDto, Project project) {
        project.setCode(projectSaveDto.getCode());
        project.setName(projectSaveDto.getName());
        project.setDescription(projectSaveDto.getDescription());
        project.setProjectType(projectSaveDto.getProjectType());
        project.setProjectStatus(projectSaveDto.getProjectStatus());
        project.setStartDate(projectSaveDto.getStartDate());
        project.setEndDate(projectSaveDto.getEndDate());
        project = projectRepository.save(project);
        return project;
    }

    private ProjectSaveDto getProjectSaveDto(ProjectSaveDto projectSaveDto, Project project) {
        List<EmployeeProjectSaveDto> employeeProjectSaveDtos = projectSaveDto.getProjectEmployeeSaveDtos();
        employeeProjectSaveDtos.forEach(employeeProjectSaveDto -> {
            EmployeeProject employeeProject = new EmployeeProject();
            if (employeeProjectSaveDto.getId() == null) {
                employeeProject.setProject(project);
                employeeProject.setEmployee(employeeRepository.findById(employeeProjectSaveDto.getEmployeeId()).orElse(null));
                employeeProject.setRoleProjectType(employeeProjectSaveDto.getRoleProjectType());
                employeeProject.setTemp(true);
                employeeProjectSaveDto.setId(employeeProjectRepository.save(employeeProject).getId());
            } else {
                employeeProject = employeeProjectRepository.findById(employeeProjectSaveDto.getId()).orElse(null);
                assert employeeProject != null;
                employeeProject.setRoleProjectType(employeeProjectSaveDto.getRoleProjectType());
                employeeProject.setEmployee(employeeRepository.findById(employeeProjectSaveDto.getEmployeeId()).orElse(null));
                employeeProjectRepository.save(employeeProject);
            }
        });
        return projectSaveDto;
    }
}
