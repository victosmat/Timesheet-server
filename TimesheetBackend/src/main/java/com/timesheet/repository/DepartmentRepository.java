package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Integer>{

    @Query("SELECT d FROM Department d WHERE d.name LIKE %?1%")
    List<Department> findAllbyKeyword(String keyword);
}
