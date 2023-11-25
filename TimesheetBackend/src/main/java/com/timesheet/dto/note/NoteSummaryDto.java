package com.timesheet.dto.note;

import java.time.LocalDate;

public class NoteSummaryDto {
    private LocalDate date;
    private Long totalHours;

    public NoteSummaryDto() {
    }

    public NoteSummaryDto(LocalDate date, Long totalHours) {
        this.date = date;
        this.totalHours = totalHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }
}
