package com.timesheet.dto.payslip;

public class NotifierPaySlipResponse {
    private String fullName;
    private String email;
    private String departmentName;
    private String basicSalary;
    private int countCheckinLate;
    private int countNotCheckout;
    private int countNotCheckin;
    private int countAbsence;
    private int countBonus;
    private Float totalSalary;

    public NotifierPaySlipResponse(String fullName, String email, String departmentName, String basicSalary, int countCheckinLate, int countNotCheckout, int countNotCheckin, int countAbsence, int countBonus, Float totalSalary) {
        this.fullName = fullName;
        this.email = email;
        this.departmentName = departmentName;
        this.basicSalary = basicSalary;
        this.countCheckinLate = countCheckinLate;
        this.countNotCheckout = countNotCheckout;
        this.countNotCheckin = countNotCheckin;
        this.countAbsence = countAbsence;
        this.countBonus = countBonus;
        this.totalSalary = totalSalary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public int getCountCheckinLate() {
        return countCheckinLate;
    }

    public void setCountCheckinLate(int countCheckinLate) {
        this.countCheckinLate = countCheckinLate;
    }

    public int getCountNotCheckout() {
        return countNotCheckout;
    }

    public void setCountNotCheckout(int countNotCheckout) {
        this.countNotCheckout = countNotCheckout;
    }

    public int getCountNotCheckin() {
        return countNotCheckin;
    }

    public void setCountNotCheckin(int countNotCheckin) {
        this.countNotCheckin = countNotCheckin;
    }

    public int getCountAbsence() {
        return countAbsence;
    }

    public void setCountAbsence(int countAbsence) {
        this.countAbsence = countAbsence;
    }

    public int getCountBonus() {
        return countBonus;
    }

    public void setCountBonus(int countBonus) {
        this.countBonus = countBonus;
    }

    public Float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Float totalSalary) {
        this.totalSalary = totalSalary;
    }
}
