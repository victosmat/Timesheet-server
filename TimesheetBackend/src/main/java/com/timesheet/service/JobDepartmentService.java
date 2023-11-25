package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.JobDepartment;
import com.timesheet.dto.department.JobDepartmentDto;

import java.util.List;

public interface JobDepartmentService {
    List<JobDepartment> findAll(String keyword);

    JobDepartment saveJobDepartment(JobDepartmentDto jobDepartmentDto);

    JobDepartment findById(Integer id);

    Boolean deleteJobDepartment(Integer id);
}
