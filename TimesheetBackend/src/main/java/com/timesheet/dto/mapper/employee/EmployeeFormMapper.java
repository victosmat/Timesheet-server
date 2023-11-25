package com.timesheet.dto.mapper.employee;

import com.manage.employeemanagementmodel.entity.Employee;
import com.timesheet.dto.employee.EmployeeFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeFormMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "hiringDate", target = "hiringDate")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "buddyId", target = "buddy.id")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "jobDepartmentId", target = "jobDepartment.id")
    @Mapping(source = "photo", target = "photo")
    Employee employeeFormDtoToEmployee(EmployeeFormDto employeeFormDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "hiringDate", target = "hiringDate")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "buddy.id", target = "buddyId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "jobDepartment.id", target = "jobDepartmentId")
    @Mapping(source = "photo", target = "photo")
    EmployeeFormDto employeeToEmployeeFormDto(Employee employee);

}
