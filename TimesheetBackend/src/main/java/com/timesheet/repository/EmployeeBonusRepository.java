package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.timesheet.dto.bonus.EmployeeBonusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeBonusRepository extends JpaRepository<EmployeeBonus, Integer> {
    @Query("select new com.timesheet.dto.bonus.EmployeeBonusDto(eb.id, eb.bonus.id, eb.bonus.name, eb.bonus.description, eb.dateBonus, eb.reason, eb.bonus.gratuity)" +
            "from EmployeeBonus eb " +
            "where eb.employee.id = ?1")
    List<EmployeeBonusDto> getByEmployeeId(Integer employeeId);

    @Query("select eb from EmployeeBonus eb " +
            "where eb.bonus.name like %?1% " +
            "and month(eb.dateBonus) = ?2 " +
            "and year(eb.dateBonus) = ?3 " +
            "and eb.employee.id = ?4")
    List<EmployeeBonus> getAll(String bonusName, Integer month, Integer year, Integer employeeId);
}
