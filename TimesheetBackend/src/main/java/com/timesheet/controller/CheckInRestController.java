package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import com.timesheet.dto.checkin.ICheckInManageDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.service.CheckInService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/app/checkin")
@SecurityRequirement(name = "bearer-key")
public class CheckInRestController {
    private final CheckInService checkInService;
    private final EmployeeRepository employeeRepository;

    public CheckInRestController(CheckInService checkInService, EmployeeRepository employeeRepository) {
        this.checkInService = checkInService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("checkin_per_month")
    public ResponseEntity<List<CheckInDto>> getCheckinPerMonth(@RequestBody CheckInRequestDto request) {
        try {
            return ResponseEntity.ok(checkInService.getAllCheckInBySpecificMonthAndYear(request));
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("get_checkin_of_employee_and_punishment")
    public ResponseEntity<Page<CheckinPunishmentResDto>> getCheckinOfEmployeeAndPunishment(@RequestParam(name = "pageNum") Integer pageNum,
                                                                                           @RequestParam(name = "pageSize") Integer pageSize,
                                                                                           @RequestParam(name = "sortField") String sortField,
                                                                                           @RequestParam(name = "sortDir") String sortDir,
                                                                                           @RequestParam(name = "employeeId") Integer employeeId,
                                                                                           @RequestParam(value = "status", required = false) CheckInStatus status,
                                                                                           @RequestParam(value = "month", required = false) Integer month,
                                                                                           @RequestParam(value = "year", required = false) Integer year,
                                                                                           @RequestParam(value = "isComplain", required = false) Boolean isComplain,
                                                                                           @RequestParam(value = "isManage", required = false) Boolean isManage) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return ResponseEntity.ok(checkInService.getCheckinOfEmployeeAndPunishmentByStatus(employeeId, status, month, year, isComplain, pageable, isManage));
    }

    @GetMapping("get_all_checkin_of_and_punishment")
    public ResponseEntity<Page<ICheckInManageDto>> getAllCheckinAndPunishment(@RequestParam(name = "pageNum") Integer pageNum,
                                                                              @RequestParam(name = "pageSize") Integer pageSize,
                                                                              @RequestParam(name = "sortField") String sortField,
                                                                              @RequestParam(name = "sortDir") String sortDir,
                                                                              @RequestParam(value = "keyword", required = false) String keyword,
                                                                              @RequestParam(value = "month", required = false) Integer month,
                                                                              @RequestParam(value = "year", required = false) Integer year,
                                                                              @RequestParam(value = "department", required = false) String department) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return ResponseEntity.ok(checkInService.getAllCheckinAndPunishment(keyword, month, year, department, pageable));
    }


    @GetMapping("save_checkpoint_time")
    public ResponseEntity<?> saveCheckpointTime(@RequestParam("employeeId") Integer employeeId,
                                                @RequestParam("checkPointTime") String checkPointTime) {
        try {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.parse(checkPointTime), ZoneId.systemDefault());
            return ResponseEntity.ok(checkInService.saveCheckPointInDay(employeeId, localDateTime));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    //    @Scheduled(cron = "0 * ${spring.scheduled.hour.structure} * * ${spring.scheduled.day}")
    @Scheduled(cron = "0 0 7 * * *")
    public void checkPunishmentCheckin() {
        LocalDate localDate = LocalDate.now();
        List<Integer> employeeIdsNotAbsenceInDay = checkInService.getAllEmployeeNotAbsenceInDay(localDate);
        employeeIdsNotAbsenceInDay.forEach(employeeId -> checkInService.checkPunishmentCheckin(employeeId, localDate));
    }

    //    @Scheduled(cron = "0 0 0 * * ${spring.scheduled.day}")
    @Scheduled(cron = "0 0 7 * * *")
    public void initDateCheckin() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> checkInService.saveInitDate(employee.getId(), localDateTime));
    }

    @PutMapping("update_status")
    public ResponseEntity<?> updateStatus(@RequestParam("id") Integer id,
                                          @RequestParam("status") String status) {
        try {
            return ResponseEntity.ok(checkInService.updateStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
