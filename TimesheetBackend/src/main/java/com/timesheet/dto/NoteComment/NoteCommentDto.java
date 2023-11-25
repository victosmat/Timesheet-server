package com.timesheet.dto.NoteComment;

public class NoteCommentDto {
    private Integer id;
    private String comment;
    private Integer noteId;
    private Integer employeeId;

    public NoteCommentDto() {
    }

    public NoteCommentDto(Integer id, String comment, Integer noteId, Integer employeeId) {
        this.id = id;
        this.comment = comment;
        this.noteId = noteId;
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
