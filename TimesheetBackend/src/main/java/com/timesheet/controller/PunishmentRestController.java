package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Punishment;
import com.timesheet.dto.punishment.PunishmentDto;
import com.timesheet.service.PunishmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/punishments")
@SecurityRequirement(name = "bearer-key")
public class PunishmentRestController {
    private final PunishmentService punishmentService;
    public PunishmentRestController(PunishmentService punishmentService) {
        this.punishmentService = punishmentService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<PunishmentDto>> getAll(@RequestParam("employeeId") Long employeeId,
                                                      @RequestParam("month") Integer month,
                                                      @RequestParam("year") Integer year,
                                                      @RequestParam("isDeleted") Boolean isDeleted) {
        return ResponseEntity.ok(punishmentService.getAll(employeeId, month, year, isDeleted));
    }

    @PutMapping("/update_complain")
    public ResponseEntity<PunishmentDto> updateComplain(@RequestParam("checkinId") Integer id,
                                                     @RequestParam("complain") String complain) {
        return ResponseEntity.ok(punishmentService.updateComplain(id, complain));
    }

    @PutMapping("/update_complain_reply")
    public ResponseEntity<PunishmentDto> updateComplainReply(@RequestParam("checkinId") Integer id,
                                                          @RequestParam("complainReply") String complainReply) {
        return ResponseEntity.ok(punishmentService.updateComplainReply(id, complainReply));
    }

    @PutMapping("/update_isDeleted")
    public ResponseEntity<PunishmentDto> updateIsDeleted(@RequestParam("checkinId") Integer id,
                                                      @RequestParam("isDeleted") Boolean isDeleted) {
        return ResponseEntity.ok(punishmentService.updateIsDeleted(id, isDeleted));
    }
}
