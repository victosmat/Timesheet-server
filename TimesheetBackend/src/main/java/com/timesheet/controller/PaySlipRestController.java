package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.payslip.PayslipDto;
import com.timesheet.service.PayslipService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/pay_slip")
@SecurityRequirement(name = "bearer-key")
public class PaySlipRestController {

    private final PayslipService payslipService;

    public PaySlipRestController(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void initPayslip() {
        payslipService.initPayslip();
    }

    @GetMapping("generate_payslip")
    public ResponseEntity<List<PayslipDto>> generatePayslip(
            @RequestParam(value = "keyword", required = false) Integer keyword,
            @RequestParam(value = "paymentStatus", required = false) Boolean paymentStatus,
            @RequestParam(value = "level", required = false) DepartmentLevelStatus level,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "branch", required = false) String branch,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year) {
        return ResponseEntity.ok(payslipService.generatePayslip());
    }
}
