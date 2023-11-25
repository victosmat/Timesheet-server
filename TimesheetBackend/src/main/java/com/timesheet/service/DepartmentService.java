package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Department;
import com.timesheet.dto.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {
	List<Department> findAll(String keyword);
    Department saveDepartment(DepartmentDto departmentDto);

    Department findById(Integer id);

    Boolean deleteDepartment(Integer id);
}
