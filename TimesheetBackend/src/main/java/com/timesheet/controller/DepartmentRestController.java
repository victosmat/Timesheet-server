package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Department;
import com.timesheet.dto.department.DepartmentDto;
import com.timesheet.service.DepartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/departments")
@SecurityRequirement(name = "bearer-key")
public class DepartmentRestController {
    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Department>> getAllDepartments(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(departmentService.findAll(keyword));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Department> getById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            return ResponseEntity.ok(departmentService.saveDepartment(departmentDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteDepartment(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.ok(departmentService.deleteDepartment(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
