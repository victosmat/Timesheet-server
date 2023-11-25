package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Role;
import com.timesheet.dto.account.RoleDto;
import com.timesheet.repository.RoleRepository;
import com.timesheet.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<RoleDto> getAllRole() {
        return roleRepository.getAllRole();
    }
}
