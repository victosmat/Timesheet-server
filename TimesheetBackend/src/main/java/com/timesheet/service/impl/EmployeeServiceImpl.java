package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.employee.BuddyDto;
import com.timesheet.dto.employee.EmployeeFormDto;
import com.timesheet.dto.employee.IEmployeeProfileDto;
import com.timesheet.dto.StaffViewDto;
import com.timesheet.dto.mapper.employee.EmployeeFormMapper;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.EmployeeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Scope("prototype")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    static int a = 0;
    private final EmployeeRepository employeeRepository;
    private final EmployeeFormMapper employeeFormMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeFormMapper employeeFormMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeFormMapper = employeeFormMapper;
        a++;
        System.out.println(a);
    }

    @Override
    @Cacheable(value = "employees", key = "#id")
    public CheckInDto.ProfileDto getEmployeeInfo(Integer id) {
        return employeeRepository.getEmployeeInfo(id);
    }

    @Override
    public Integer getEmployeeId(String username) {
        return employeeRepository.getEmployeeId(username);
    }

    @Override
    public Page<StaffViewDto> getStaffListByNativeQuery(Integer buddyId, Integer pageNumber, Integer pageSize, String nameSearch, String sortField, String sortOrder) {
        Sort sort = switch (sortField) {
            case "jobDepartment" -> Sort.by("jobDepartment.name");
            case "department" -> Sort.by("department.name");
            case "fullName" -> Sort.by("first_name").and(Sort.by("last_name"));
            default -> Sort.by("id");
        };

        sort = (sortOrder.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return employeeRepository.getAllStaffByBuddyId(buddyId, pageable);
    }

    @Override
    public Page<IEmployeeProfileDto> listByPageWithIsEnable(Integer pageNum, Integer pageSize, String sortField, String sortDir, String keyword, Boolean isActive, String departmentLevelStatus, String jobDepartment, String department) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return employeeRepository.findAllWithIsActiveAndLevel(keyword, pageable, isActive, departmentLevelStatus, jobDepartment, department);
    }

    @Override
    public Page<IEmployeeProfileDto> listByPage(Integer pageNum, Integer pageSize, String sortField, String sortDir, String keyword, String departmentLevelStatus, String jobDepartment, String department) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return employeeRepository.findAllWithLevel(keyword, pageable, departmentLevelStatus, jobDepartment, department);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeFormDto findEmployeeFormById(Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).get();
        return employeeFormMapper.employeeToEmployeeFormDto(employee);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void detete(Integer employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).get();
        if (employee.getBuddy() != null) {
            Set<Employee> employees = getSubEmployee(employee.getBuddy().getId());
            System.out.println(employees);
            employees.remove(employee);
            employee.setBuddy(null);
        }
        for (Employee associatedEmployee : getSubEmployee(employeeId)) {
            associatedEmployee.setBuddy(null);
            employeeRepository.save(associatedEmployee);
        }
        employeeRepository.save(employee);

        employeeRepository.delete(employee);
    }

    private Set<Employee> getSubEmployee(Integer parentEmployeeId) {
        return employeeRepository.findSubEmployeeByParentEmployee(parentEmployeeId);
    }

    @Override
    public List<Employee> findByIdNot(Integer id) {
        return employeeRepository.findByIdNot(id);
    }

    @Override
    public boolean checkEmailUnique(Integer id, String email) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if (employee == null) return true;
        boolean isCreatingNew = (id == null);
        if (isCreatingNew) {
            return false;
        } else {
            return Objects.equals(employee.getId(), id);
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<String> getRolesById(Integer id) {
        return employeeRepository.getRolesById(id);
    }

    @Override
    public List<BuddyDto> getBuddys() {
        return employeeRepository.getBuddys();
    }
}
