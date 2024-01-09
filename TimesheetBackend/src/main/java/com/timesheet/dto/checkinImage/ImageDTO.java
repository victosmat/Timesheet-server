package com.timesheet.dto.checkinImage;

public class ImageDTO {
    private String employeeId;

    public ImageDTO() {
    }

    public ImageDTO(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
