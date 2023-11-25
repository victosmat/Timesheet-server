package com.manage.employeemanagementmodel.entity;

import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_in")
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "current_date_checkin")
    private LocalDateTime currentDate;
    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;
    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CheckInStatus status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public CheckIn() {
    }

    public CheckIn(Integer id, LocalDateTime currentDate, LocalDateTime checkInTime, LocalDateTime checkOutTime, CheckInStatus status, Employee employee) {
        this.id = id;
        this.currentDate = currentDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.employee = employee;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public CheckInStatus getStatus() {
        return status;
    }

    public void setStatus(CheckInStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
