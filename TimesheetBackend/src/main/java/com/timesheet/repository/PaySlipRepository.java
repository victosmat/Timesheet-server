package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Payslip;
import com.manage.employeemanagementmodel.entity.enums.DepartmentLevelStatus;
import com.timesheet.dto.payslip.PayslipDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaySlipRepository extends JpaRepository<Payslip, Integer> {

    @Query("SELECT p FROM Payslip p WHERE p.employee.id = ?1 AND MONTH(p.payDay) = ?2 AND YEAR(p.payDay) = ?3")
    Payslip findByEmployeeIdAndMonthAndYear(Integer id, int month, int year);

    @Query("SELECT new com.timesheet.dto.payslip.PayslipDto(p.id, e.id, CONCAT(e.firstName, ' ', e.lastName), e.email, e.department.name, e.employeeLevelStatus, p.payDay, p.totalSalary, p.paymentStatus) " +
            "FROM Payslip p " +
            "LEFT JOIN Employee e ON p.employee.id = e.id " +
            "LEFT JOIN Department d ON e.department.id = d.id " +
            "WHERE (e.lastName LIKE %?1% OR e.firstName LIKE %?1%) " +
            "AND e.enabled = true " +
            "AND (p.paymentStatus = ?2 OR ?2 IS NULL) " +
            "AND (e.employeeLevelStatus = ?3 OR ?3 IS NULL) " +
            "AND (month(p.payDay) = ?4 OR ?4 IS NULL) " +
            "AND (year(p.payDay) = ?5 OR ?5 IS NULL)" +
            "AND (e.department.name LIKE %?6%) ")
    Page<PayslipDto> viewPayslipByStatus(String keyword, Boolean paymentStatus, DepartmentLevelStatus level, Integer month, Integer year, String branch, Pageable pageable);

    @Query("SELECT new com.timesheet.dto.payslip.PayslipDto(p.id, e.id, CONCAT(e.firstName, ' ', e.lastName), e.email, e.department.name, e.employeeLevelStatus, p.payDay, p.totalSalary, p.paymentStatus) " +
            "FROM Payslip p " +
            "LEFT JOIN Employee e ON p.employee.id = e.id " +
            "LEFT JOIN Department d ON e.department.id = d.id " +
            "WHERE (e.lastName LIKE %?1% OR e.firstName LIKE %?1%) " +
            "AND e.enabled = true " +
            "AND (e.employeeLevelStatus = ?2 OR ?2 IS NULL) " +
            "AND (month(p.payDay) = ?3 OR ?3 IS NULL) " +
            "AND (year(p.payDay) = ?4 OR ?4 IS NULL)" +
            "AND (e.department.name LIKE %?5%) ")
    Page<PayslipDto> viewPayslip(String keyword, DepartmentLevelStatus level, Integer month, Integer year, String branch, Pageable pageable);
}
