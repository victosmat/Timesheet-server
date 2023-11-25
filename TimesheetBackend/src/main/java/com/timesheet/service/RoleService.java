package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Role;
import com.timesheet.dto.account.RoleDto;

import java.util.List;

public interface RoleService {
    Role getRoleById(Integer id);

    List<RoleDto> getAllRole();
}
