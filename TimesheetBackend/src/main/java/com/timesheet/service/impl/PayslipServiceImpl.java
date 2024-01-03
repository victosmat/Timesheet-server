package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.manage.employeemanagementmodel.entity.Payslip;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import com.timesheet.dto.payslip.PayslipDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.repository.PaySlipRepository;
import com.timesheet.service.AbsenceService;
import com.timesheet.service.CheckInService;
import com.timesheet.service.EmployeeBonusService;
import com.timesheet.service.PayslipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class PayslipServiceImpl implements PayslipService {
    private final EmployeeRepository employeeRepository;
    private final CheckInService checkInService;
    private final PaySlipRepository paySlipRepository;
    private final AbsenceService absenceService;
    private final EmployeeBonusService employeeBonusService;

    public PayslipServiceImpl(EmployeeRepository employeeRepository, CheckInService checkInService, PaySlipRepository paySlipRepository, AbsenceService absenceService, EmployeeBonusService employeeBonusService) {
        this.employeeRepository = employeeRepository;
        this.checkInService = checkInService;
        this.paySlipRepository = paySlipRepository;
        this.absenceService = absenceService;
        this.employeeBonusService = employeeBonusService;
    }

    @Override
    public void initPayslip() {
        employeeRepository.findAll().forEach(this::initPayslipByEmployee);
    }

    @Override
    public Page<PayslipDto> viewPayslip(Pageable pageable, String keyword, Boolean paymentStatus, DepartmentLevelStatus level, String branch, Integer month, Integer year) {
        if (Objects.nonNull(paymentStatus))
            return paySlipRepository.viewPayslipByStatus(keyword, paymentStatus, level, month, year, branch, pageable);
        return paySlipRepository.viewPayslip(keyword, level, month, year, branch, pageable);
    }

    @Override
    public Boolean updatePayslip(Integer id, Boolean paymentStatus) {
        Payslip payslip = paySlipRepository.findById(id).orElse(null);
        if (Objects.isNull(payslip)) return false;
        payslip.setPaymentStatus(paymentStatus);
        paySlipRepository.save(payslip);
        return true;
    }


    @Async
    public void initPayslipByEmployee(Employee employee) {


        double totalMoney = getTotalSalary(employee, Pageable.unpaged());

        Payslip payslipSaved = new Payslip();
        payslipSaved.setPayDay(LocalDate.now());
        payslipSaved.setEmployee(employee);
        payslipSaved.setTotalSalary((long) totalMoney);
        payslipSaved.setPaymentStatus(false);

        Payslip payslip = paySlipRepository.findByEmployeeIdAndMonthAndYear(employee.getId(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        if (payslip != null) payslipSaved.setId(payslip.getId());
        paySlipRepository.save(payslipSaved);
    }

    public Double getTotalSalary(Employee employee, Pageable pageable) {
        Calendar calendar = Calendar.getInstance();

        int month = (calendar.get(Calendar.MONTH) == Calendar.JANUARY) ? 12 : calendar.get(Calendar.MONTH);
//        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR) - 1;
        long salaryRange = employee.getJobDepartment().getSalaryRange();
        Double salaryRangeInHour = (double) (salaryRange / 20 / 8);

        List<CheckinPunishmentResDto> checkinPunishmentRes = checkInService.getCheckinOfEmployeeAndPunishmentByStatus(
                        employee.getId(), null, month, year, null, pageable, false)
                .stream().filter(checkinPunishmentResDto -> Objects.nonNull(checkinPunishmentResDto.getPunishmentTypeDes())).toList();

        double totalPunishmentMoney = checkinPunishmentRes.stream().mapToDouble(CheckinPunishmentResDto::getPunishmentMoney).sum();

        YearMonth yearMonthToFilter = YearMonth.of(year, month);
        List<Absence> filteredAbsences = employee.getAbsences().stream()
                .filter(absence -> {
                    LocalDate dateRequest = absence.getDateRequest();
                    YearMonth yearMonthAbsence = YearMonth.of(dateRequest.getYear(), dateRequest.getMonth());
                    return yearMonthAbsence.equals(yearMonthToFilter);
                }).toList();

        Double totalHoursAbsence = filteredAbsences.stream().mapToDouble(Absence::getTimeOff).sum();
        double totalMoneyAbsence = totalHoursAbsence * salaryRangeInHour;

        List<EmployeeBonus> filteredBonuses = employee.getEmployeeBonuses().stream()
                .filter(employeeBonus -> {
                    LocalDate dateRequest = employeeBonus.getDateBonus();
                    YearMonth yearMonthBonus = YearMonth.of(dateRequest.getYear(), dateRequest.getMonth());
                    return yearMonthBonus.equals(yearMonthToFilter);
                }).toList();

        double totalMoneyBonus = filteredBonuses.stream()
                .mapToDouble(employeeBonus -> employeeBonus.getBonus().getGratuity())
                .sum();

        return salaryRange - totalMoneyAbsence + totalMoneyBonus - totalPunishmentMoney;
    }
}
