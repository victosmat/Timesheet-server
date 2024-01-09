package com.timesheet.dto.checkinImage;

import java.util.List;

public class SaveImagesDTO {
    private String employeeId;
    private List<String> images;

    public SaveImagesDTO() {
    }

    public SaveImagesDTO(String employeeId, List<String> images) {
        this.employeeId = employeeId;
        this.images = images;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
