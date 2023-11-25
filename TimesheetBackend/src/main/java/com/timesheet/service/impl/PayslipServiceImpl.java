package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.EmployeeBonus;
import com.manage.employeemanagementmodel.entity.Payslip;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import com.timesheet.dto.payslip.PayslipDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.repository.PaySlipRepository;
import com.timesheet.service.AbsenceService;
import com.timesheet.service.CheckInService;
import com.timesheet.service.EmployeeBonusService;
import com.timesheet.service.PayslipService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
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
    public List<PayslipDto> generatePayslip() {
        List<PayslipDto> payslipList = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> payslipList.add(generatePayslipByEmployee(employee)));
        return payslipList;
    }

    @Async
    public PayslipDto generatePayslipByEmployee(Employee employee) {
        return new PayslipDto(null, employee.getId(), employee.getFirstName() + " " + employee.getLastName(), employee.getEmail(), employee.getDepartment().getName(), employee.getEmployeeLevelStatus(), LocalDate.now().toString(), getTotalSalary(employee), false);
    }

    @Async
    public void initPayslipByEmployee(Employee employee) {

        double totalMoney = getTotalSalary(employee);

        Payslip payslipSaved = new Payslip();
        payslipSaved.setPayDay(LocalDate.now());
        payslipSaved.setEmployee(employee);
        payslipSaved.setTotalSalary((long) totalMoney);
        payslipSaved.setPaymentStatus(false);

        Payslip payslip = paySlipRepository.findByEmployeeIdAndMonthAndYear(employee.getId(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        if (payslip != null) payslipSaved.setId(payslip.getId());
        paySlipRepository.save(payslipSaved);
    }

    public Double getTotalSalary(Employee employee) {
        Calendar calendar = Calendar.getInstance();

//        int month = (calendar.get(Calendar.MONTH) == Calendar.JANUARY) ? 12 : calendar.get(Calendar.MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        long salaryRange = employee.getJobDepartment().getSalaryRange();
        Double salaryRangeInHour = (double) (salaryRange / 20 / 8);

        List<CheckinPunishmentResDto> checkinPunishmentRes = checkInService.getCheckinOfEmployeeAndPunishmentByStatus(
                        employee.getId(), null, month, year, null)
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
