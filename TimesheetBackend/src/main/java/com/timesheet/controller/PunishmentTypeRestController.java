package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.PunishmentType;
import com.timesheet.dto.punishment.PunishmentTypeDto;
import com.timesheet.service.PunishmentTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/punishment_types")
@SecurityRequirement(name = "bearer-key")
public class PunishmentTypeRestController {
    private final PunishmentTypeService punishmentTypeService;

    public PunishmentTypeRestController(PunishmentTypeService punishmentTypeService) {
        this.punishmentTypeService = punishmentTypeService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<PunishmentType>> getAll() {
        return ResponseEntity.ok(punishmentTypeService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<PunishmentType> save(@RequestBody PunishmentTypeDto punishmentTypeDto) {
        return ResponseEntity.ok(punishmentTypeService.save(punishmentTypeDto));
    }
}