package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaySlipRepository extends JpaRepository<Payslip, Integer> {

    @Query("SELECT p FROM Payslip p WHERE p.employee.id = ?1 AND MONTH(p.payDay) = ?2 AND YEAR(p.payDay) = ?3")
    Payslip findByEmployeeIdAndMonthAndYear(Integer id, int month, int year);
}
