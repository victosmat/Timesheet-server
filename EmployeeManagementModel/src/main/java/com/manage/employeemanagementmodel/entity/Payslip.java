package com.manage.employeemanagementmodel.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pay_slip")
public class Payslip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date", nullable = false)
    private LocalDate payDay;

    @Column(name = "total_salary", nullable = false)
    private Long totalSalary;

    @Column(name = "payment_status")
    private Boolean paymentStatus;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Payslip() {
    }

    public Payslip(Integer id, LocalDate payDay, Long totalSalary, Boolean paymentStatus, Employee employee) {
        this.id = id;
        this.payDay = payDay;
        this.totalSalary = totalSalary;
        this.paymentStatus = paymentStatus;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
