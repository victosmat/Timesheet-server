package com.timesheet.service;

import com.timesheet.dto.payslip.PayslipDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PayslipService {
    void initPayslip();

    List<PayslipDto> generatePayslip();
}
