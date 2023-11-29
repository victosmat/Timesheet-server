package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.payslip.PayslipDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PayslipService {
    void initPayslip();

    Page<PayslipDto> viewPayslip(Pageable pageable, String keyword, Boolean paymentStatus, DepartmentLevelStatus level, String branch, Integer month, Integer year);
}
