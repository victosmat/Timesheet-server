package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.timesheet.dto.bonus.EmployeeBonusDto;
import com.timesheet.dto.bonus.EmployeeBonusSaveDto;
import com.timesheet.service.EmployeeBonusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/employee_bonus")
@SecurityRequirement(name = "bearer-key")
public class EmployeeBonusRestController {
    private final EmployeeBonusService employeeBonusService;

    public EmployeeBonusRestController(EmployeeBonusService employeeBonusService) {
        this.employeeBonusService = employeeBonusService;
    }

    // ngày quốc khánh
    @Scheduled(cron = "0 0 0 30 4 *")
    public void saveIndependenceDay() {
        employeeBonusService.saveIndependenceDay();
    }

    // tết
    @Scheduled(cron = "0 0 0 1 1 *")
    public void saveNewYear() {
        employeeBonusService.saveNewYear();
    }

    @GetMapping("/get_by_employeeId")
    public ResponseEntity<List<EmployeeBonusDto>> getByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        return ResponseEntity.ok(employeeBonusService.getByEmployeeId(employeeId));
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<EmployeeBonus>> getAll(@RequestParam("bonusName") String bonusName,
                                                      @RequestParam("month") Integer month,
                                                      @RequestParam("year") Integer year,
                                                      @RequestParam("employeeId") Integer employeeId) {
        return ResponseEntity.ok(employeeBonusService.getAll(bonusName, month, year, employeeId));
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeBonus> save(@RequestBody EmployeeBonusSaveDto employeeBonusSaveDto) {
        return ResponseEntity.ok(employeeBonusService.save(employeeBonusSaveDto));
    }
}
