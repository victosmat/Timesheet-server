package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.JobDepartment;
import com.timesheet.dto.department.JobDepartmentDto;
import com.timesheet.service.JobDepartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/job_departments")
@SecurityRequirement(name = "bearer-key")
public class JobDepartmentRestController {
    private final JobDepartmentService jobDepartmentService;

    public JobDepartmentRestController(JobDepartmentService jobDepartmentService) {
        this.jobDepartmentService = jobDepartmentService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<JobDepartment>> getAllJobDepartments(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(jobDepartmentService.findAll(keyword));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<JobDepartment> getById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(jobDepartmentService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<JobDepartment> saveJobDepartment(@RequestBody JobDepartmentDto jobDepartmentDto) {
        try {
            return ResponseEntity.ok(jobDepartmentService.saveJobDepartment(jobDepartmentDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteJobDepartment(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(jobDepartmentService.deleteJobDepartment(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
