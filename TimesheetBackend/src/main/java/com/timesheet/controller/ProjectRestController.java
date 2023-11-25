package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.timesheet.dto.project.*;
import com.timesheet.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/projects")
@SecurityRequirement(name = "bearer-key")
public class ProjectRestController {
    private final ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<ProjectViewResDto>> getAllProjectsForFormByEmployeeId(@RequestParam("status") String status,
                                                                                     @RequestParam("nameKey") String name) {
        if (status.equals("ALL"))
            return ResponseEntity.ok(mapToProjectViewDto(projectService.getAllProjectsForForm(name)));
        return ResponseEntity.ok(mapToProjectViewDto(projectService.getAllProjectsForFormByStatus(ProjectStatus.valueOf(status), name)));
    }

    public List<ProjectViewResDto> mapToProjectViewDto(List<ProjectViewDto> projectViewDtos){
        return projectViewDtos.stream().map(projectViewDto ->
                new ProjectViewResDto(
                        projectViewDto.getId(),
                        projectViewDto.getCode(),
                        projectViewDto.getName(),
                        projectViewDto.getDescription(),
                        projectViewDto.getPmName(),
                        projectViewDto.getTotalEmployee(),
                        projectViewDto.getStartDate().toString(),
                        projectViewDto.getEndDate().toString()
                )).toList();
    }

    @GetMapping("/get_details")
    public ResponseEntity<ProjectDetailViewDto> getProjectDetails(@RequestParam("projectId") Integer projectId) {
        return ResponseEntity.ok(projectService.getProjectDetails(projectId));
    }

    @PostMapping("/save")
    public ResponseEntity<ProjectSaveDto> saveProject(@RequestBody ProjectSaveDto projectSaveDto) {
        System.out.println(projectSaveDto);
        return ResponseEntity.ok(projectService.saveProject(projectSaveDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProject(@RequestParam("projectId") Integer projectId) {
        return ResponseEntity.ok(projectService.deleteProject(projectId));
    }
}
