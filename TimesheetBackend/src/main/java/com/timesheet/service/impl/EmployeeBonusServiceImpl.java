package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.timesheet.dto.bonus.EmployeeBonusDto;
import com.timesheet.dto.bonus.EmployeeBonusSaveDto;
import com.timesheet.repository.BonusRepository;
import com.timesheet.repository.EmployeeBonusRepository;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.EmailService;
import com.timesheet.service.EmployeeBonusService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmployeeBonusServiceImpl implements EmployeeBonusService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeBonusRepository employeeBonusRepository;
    private final BonusRepository bonusRepository;

    public EmployeeBonusServiceImpl(EmployeeRepository employeeRepository, EmployeeBonusRepository employeeBonusRepository, BonusRepository bonusRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeBonusRepository = employeeBonusRepository;
        this.bonusRepository = bonusRepository;
    }

    @Override
    public void saveIndependenceDay() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> {
            EmployeeBonus employeeBonus = new EmployeeBonus();
            employeeBonus.setDateBonus(LocalDate.now());
            employeeBonus.setReason("Independence Day");
            employeeBonus.setEmployee(employee);
            employeeBonus.setBonus(bonusRepository.findById(2).orElse(null));
            employeeBonusRepository.save(employeeBonus);
        });
    }

    @Override
    public void saveNewYear() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> {
            EmployeeBonus employeeBonus = new EmployeeBonus();
            employeeBonus.setDateBonus(LocalDate.now());
            employeeBonus.setReason("New Year");
            employeeBonus.setEmployee(employee);
            employeeBonus.setBonus(bonusRepository.findById(3).orElse(null));
            employeeBonusRepository.save(employeeBonus);
        });
    }

    @Override
    public List<EmployeeBonusDto> getByEmployeeId(Integer employeeId) {
        return employeeBonusRepository.getByEmployeeId(employeeId);
    }

    @Override
    public List<EmployeeBonus> getAll(String bonusName, Integer month, Integer year, Integer employeeId) {
        return employeeBonusRepository.getAll(bonusName, month, year, employeeId);
    }

    @Override
    public EmployeeBonus save(EmployeeBonusSaveDto employeeBonusSaveDto) {
        LocalDate dateBonus = LocalDate.now();
        if (employeeBonusSaveDto.getId() == null) {
            EmployeeBonus employeeBonus = new EmployeeBonus();
            employeeBonus.setDateBonus(dateBonus);
            employeeBonus.setReason(employeeBonusSaveDto.getReason());
            employeeBonus.setEmployee(employeeRepository.findById(employeeBonusSaveDto.getEmployeeId()).orElse(null));
            employeeBonus.setBonus(bonusRepository.findById(employeeBonusSaveDto.getBonusId()).orElse(null));
            return employeeBonusRepository.save(employeeBonus);
        }
        EmployeeBonus employeeBonus = employeeBonusRepository.findById(employeeBonusSaveDto.getId()).orElse(null);
        if (employeeBonus == null) {
            return null;
        }
        employeeBonus.setDateBonus(dateBonus);
        employeeBonus.setReason(employeeBonusSaveDto.getReason());
        employeeBonus.setEmployee(employeeRepository.findById(employeeBonusSaveDto.getEmployeeId()).orElse(null));
        employeeBonus.setBonus(bonusRepository.findById(employeeBonusSaveDto.getBonusId()).orElse(null));
        return employeeBonusRepository.save(employeeBonus);
    }

    @Override
    public void delete(Integer id) {
        employeeBonusRepository.deleteById(id);
    }
}
