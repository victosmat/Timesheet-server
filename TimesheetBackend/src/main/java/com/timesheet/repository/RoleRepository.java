package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Role;
import com.timesheet.dto.account.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT new com.timesheet.dto.account.RoleDto(r.id, r.name) FROM Role r")
    List<RoleDto> getAllRole();
}
