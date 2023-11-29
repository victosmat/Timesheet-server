package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.payslip.PayslipDto;
import com.timesheet.service.PayslipService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("view_payslip")
    public ResponseEntity<Page<PayslipDto>> generatePayslip(@RequestParam(name = "pageNum") Integer pageNum,
                                                            @RequestParam(name = "pageSize") Integer pageSize,
                                                            @RequestParam(name = "sortField") String sortField,
                                                            @RequestParam(name = "sortDir") String sortDir,
                                                            @RequestParam(value = "keyword", required = false) String keyword,
                                                            @RequestParam(value = "paymentStatus", required = false) String paymentStatus,
                                                            @RequestParam(value = "level", required = false) DepartmentLevelStatus level,
                                                            @RequestParam(value = "branch", required = false) String branch,
                                                            @RequestParam(value = "month", required = false) Integer month,
                                                            @RequestParam(value = "year", required = false) Integer year) {
        Sort sort = Sort.by(sortField);
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Boolean paymentStatusBoolean = null;
        if (paymentStatus.equals("true") || paymentStatus.equals("false"))
            paymentStatusBoolean = Boolean.parseBoolean(paymentStatus);

        return ResponseEntity.ok(payslipService.viewPayslip(pageable, keyword, paymentStatusBoolean, level, branch, month, year));
    }
}
