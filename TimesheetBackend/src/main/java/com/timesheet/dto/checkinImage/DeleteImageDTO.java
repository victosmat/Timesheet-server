package com.timesheet.dto.checkinImage;

public class DeleteImageDTO {
    private String employeeId;
    private String nameFile;

    public DeleteImageDTO() {
    }

    public DeleteImageDTO(String employeeId, String nameFile) {
        this.employeeId = employeeId;
        this.nameFile = nameFile;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
