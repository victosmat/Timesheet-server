package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.absence.AbsenceManageViewDto;
import com.timesheet.dto.absence.AbsenceViewDto;
import com.timesheet.service.AbsenceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/absence")
@SecurityRequirement(name = "bearer-key")
public class AbsenceRestController {
    private final AbsenceService absenceService;

    public AbsenceRestController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @PutMapping("update_absence_status")
    public ResponseEntity<?> updateAbsenceStatus(@RequestParam("status") AbsenceStatus status, @RequestParam("id") Integer id) {
        try {
            absenceService.updateAbsenceStatus(id, status);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("get_absence_of_month")
    public ResponseEntity<Page<AbsenceDto>> getAbsenceStatus(@RequestParam(name = "pageNum") Integer pageNum,
                                                             @RequestParam(name = "pageSize") Integer pageSize,
                                                             @RequestParam(name = "sortField") String sortField,
                                                             @RequestParam(name = "sortDir") String sortDir,
                                                             @RequestParam("month") Integer monthNumber,
                                                             @RequestParam("year") Integer yearNumber,
                                                             @RequestParam("employeeId") Integer employeeId) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return ResponseEntity.ok(absenceService.listAllAbsenceRequestInMonthAndYearOfEmployee(monthNumber, yearNumber, employeeId, pageable));
    }

    @GetMapping("get_all_absence_of_month")
    public ResponseEntity<List<AbsenceDto>> getAllAbsenceStatus(@RequestParam("month") Integer monthNumber,
                                                                @RequestParam("year") Integer yearNumber,
                                                                @RequestParam(value = "email", required = false) String email,
                                                                @RequestParam(value = "status", required = false) AbsenceStatus absenceStatus,
                                                                @RequestParam(value = "type", required = false) TypeTimeOff typeTimeOff) {
        return ResponseEntity.ok(absenceService.listAllAbsenceRequestInMonthAndYear(monthNumber, yearNumber, email, absenceStatus, typeTimeOff));
    }

    @PostMapping("save")
    public ResponseEntity<?> saveAbsenceRequest(@RequestBody AbsenceDto absenceDto) {
        try {
            absenceService.saveAbsenceRequest(absenceDto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("get_day_absence_list")
    public ResponseEntity<List<LocalDate>> getDayAbsenceList(@RequestParam("month") Integer month,
                                                             @RequestParam("year") Integer year,
                                                             @RequestParam("employeeId") Integer employeeId) {
        return ResponseEntity.ok(absenceService.ListAllDayAbsenceInParticularMonthAndYearOfEmployee(month, year, employeeId));
    }

    @GetMapping("get_day_absence_list_all")
    public ResponseEntity<List<LocalDate>> getDayAbsenceListAll(@RequestParam("month") Integer month,
                                                                @RequestParam("year") Integer year,
                                                                @RequestParam(value = "email", required = false) String email,
                                                                @RequestParam(value = "status", required = false) AbsenceStatus absenceStatus,
                                                                @RequestParam(value = "type", required = false) TypeTimeOff typeTimeOff) {
        return ResponseEntity.ok(absenceService.ListAllDayAbsenceInParticularMonthAndYear(month, year, email, absenceStatus, typeTimeOff));
    }

    @GetMapping("get_absences_per_day")
    public ResponseEntity<List<AbsenceViewDto>> getAbsencesPerDay(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date date,
                                                                  @RequestParam("employeeId") Integer employeeId) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ResponseEntity.ok(absenceService.getAbsenceByDateAndEmployee(localDate, employeeId));
    }

    @GetMapping("get_absences_all_per_day")
    public ResponseEntity<List<AbsenceManageViewDto>> getAbsencesAllPerDay(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date date,
                                                                           @RequestParam(value = "email", required = false) String email,
                                                                           @RequestParam(value = "status", required = false) AbsenceStatus absenceStatus,
                                                                           @RequestParam(value = "type", required = false) TypeTimeOff typeTimeOff) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ResponseEntity.ok(absenceService.getAbsenceByDateAllEmployee(localDate, email, absenceStatus, typeTimeOff));
    }

    @GetMapping("find_by_id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(absenceService.findFormById(id));
    }

    @DeleteMapping("delete_by_id")
    public ResponseEntity<Boolean> deleteById(@RequestParam("id") Integer id) {
        try {
            Boolean result = absenceService.deletePendingAbsenceRequest(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("staff_absence_by_month")
    public ResponseEntity<?> getStaffAbsenceByMonthAndYear(@RequestParam("staffId") Integer staffId,
                                                           @RequestParam("month") Integer month,
                                                           @RequestParam("year") Integer year) {
        return ResponseEntity.ok().body(absenceService.listAllAbsenceOfStaffInParticularMonthAndYear(staffId, month, year));
    }

    @PutMapping("update_staff_absence_status")
    public ResponseEntity<?> updateAbsenceStatus(@RequestParam("absenceId") Integer noteId, @RequestParam("status") AbsenceStatus status) {
        absenceService.updateAbsenceStatus(noteId, status);
        return ResponseEntity.ok(true);
    }

}
