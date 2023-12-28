package com.timesheet.dto.note;

import java.time.LocalDate;

public class NoteSummaryDto {
    private LocalDate date;
    private Double totalHours;

    public NoteSummaryDto() {
    }

    public NoteSummaryDto(LocalDate date, Double totalHours) {
        this.date = date;
        this.totalHours = totalHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }
}
