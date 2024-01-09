package com.timesheet.dto.checkinImage;

public class GetImagesDTO {
    private String employeeId;
    private String month;
    private String year;

    public GetImagesDTO() {
    }

    public GetImagesDTO(String employeeId, String month, String year) {
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
