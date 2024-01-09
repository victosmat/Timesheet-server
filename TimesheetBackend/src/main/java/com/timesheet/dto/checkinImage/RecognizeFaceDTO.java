package com.timesheet.dto.checkinImage;

public class RecognizeFaceDTO {
    private String image;

    public RecognizeFaceDTO() {
    }

    public RecognizeFaceDTO(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
