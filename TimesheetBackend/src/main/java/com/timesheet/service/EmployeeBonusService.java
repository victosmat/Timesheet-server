package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.timesheet.dto.bonus.EmployeeBonusDto;
import com.timesheet.dto.bonus.EmployeeBonusSaveDto;

import java.util.List;

public interface EmployeeBonusService {
    void saveIndependenceDay();

    void saveNewYear();

    List<EmployeeBonusDto> getByEmployeeId(Integer employeeId);

    List<EmployeeBonus> getAll(String bonusName, Integer month, Integer year, Integer employeeId);

    EmployeeBonus save(EmployeeBonusSaveDto employeeBonusSaveDto);
}
