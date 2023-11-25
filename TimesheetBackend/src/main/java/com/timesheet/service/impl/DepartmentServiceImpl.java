package com.timesheet.service.impl;


import com.manage.employeemanagementmodel.entity.Department;
import com.timesheet.dto.department.DepartmentDto;
import com.timesheet.repository.DepartmentRepository;
import com.timesheet.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> findAll(String keyword) {
		return  (keyword != null) ? departmentRepository.findAllbyKeyword(keyword) :departmentRepository.findAll();
	}

	@Override
	public Department saveDepartment(DepartmentDto departmentDto) {
		if (departmentDto.getId() != null) {
			Department department = departmentRepository.findById(departmentDto.getId()).orElse(null);
			if (department != null) {
				department.setName(departmentDto.getName());
				return departmentRepository.save(department);
			}
			return null;
		}
		Department department = new Department();
		department.setName(departmentDto.getName());
		return departmentRepository.save(department);
	}

	@Override
	public Department findById(Integer id) {
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean deleteDepartment(Integer id) {
		try {
			departmentRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
