package com.timesheet.dto.note;

import java.time.LocalDate;
import java.util.List;

public class NotesPerDayDto {
    private LocalDate dateSubmit;
    private List<NoteViewDto> lst;

    public NotesPerDayDto() {
    }

    public NotesPerDayDto(LocalDate dateSubmit, List<NoteViewDto> lst) {
        this.dateSubmit = dateSubmit;
        this.lst = lst;
    }

    public LocalDate getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDate dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public List<NoteViewDto> getLst() {
        return lst;
    }

    public void setLst(List<NoteViewDto> lst) {
        this.lst = lst;
    }
}
