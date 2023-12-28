package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.JobDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobDepartmentRepository extends JpaRepository<JobDepartment, Integer> {
    @Query("SELECT jd FROM JobDepartment jd WHERE jd.name LIKE %?1% OR jd.jobDepartment LIKE %?1% OR jd.description LIKE %?1%")
    List<JobDepartment> findAllbyKeyword(String keyword);

    @Query("SELECT jd FROM JobDepartment jd WHERE jd.jobDepartment = ?1")
    JobDepartment getJobDepartmentByJobDepartment(String jobDepartment);

    Optional<JobDepartment> findByJobDepartment(String jobDepartment);
}
