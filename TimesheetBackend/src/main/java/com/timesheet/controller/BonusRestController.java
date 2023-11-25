package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Bonus;
import com.timesheet.dto.bonus.BonusDto;
import com.timesheet.service.BonusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/bonuses")
@SecurityRequirement(name = "bearer-key")
public class BonusRestController {
    private final BonusService bonusService;

    public BonusRestController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Bonus>> getAll(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(bonusService.getAll(keyword));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Bonus> getById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(bonusService.getById(id));
    }
    @PostMapping("/save")
    public ResponseEntity<Bonus> save(@RequestBody BonusDto bonusDto) {
        return ResponseEntity.ok(bonusService.save(bonusDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        bonusService.delete(id);
        return ResponseEntity.ok().build();
    }
}
