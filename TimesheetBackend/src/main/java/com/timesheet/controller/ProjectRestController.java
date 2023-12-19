package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Bonus;
import com.manage.employeemanagementmodel.entity.enums.ProjectStatus;
import com.timesheet.dto.project.*;
import com.timesheet.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<ProjectViewResDto>> getAllProjectsForFormByEmployeeId(
            @RequestParam(name = "pageNum") Integer pageNum,
            @RequestParam(name = "pageSize") Integer pageSize,
            @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "sortDir") String sortDir,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "nameKey") String name) {

        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<ProjectViewDto> projectViewDtos;
        if (status.equals("ALL")) {
            projectViewDtos = projectService.getAllProjectsForForm(name, pageable);
        } else {
            projectViewDtos = projectService.getAllProjectsForFormByStatus(ProjectStatus.valueOf(status), name, pageable);
        }

        Page<ProjectViewResDto> projectViewResDtos = projectViewDtos.map(this::mapToProjectViewDto);
        return ResponseEntity.ok(projectViewResDtos);
    }


    public ProjectViewResDto mapToProjectViewDto(ProjectViewDto projectViewDto) {
        return new ProjectViewResDto(
                projectViewDto.getId(),
                projectViewDto.getCode(),
                projectViewDto.getName(),
                projectViewDto.getDescription(),
                projectViewDto.getPmName(),
                projectViewDto.getTotalEmployee(),
                projectViewDto.getStartDate().toString(),
                projectViewDto.getEndDate().toString(),
                projectViewDto.getProjectStatus()
        );
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

    @PutMapping("/update_status")
    public ResponseEntity<Boolean> updateProjectStatus(@RequestParam("projectId") Integer projectId,
                                                     @RequestParam("status") ProjectStatus status) {
        return ResponseEntity.ok(projectService.updateProjectStatus(projectId, status));
    }
}
