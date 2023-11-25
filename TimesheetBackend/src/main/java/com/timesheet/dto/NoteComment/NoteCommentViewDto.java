package com.timesheet.dto.NoteComment;

public class NoteCommentViewDto {
    private Integer id;
    private String comment;
    private String pmName;

    public NoteCommentViewDto(Integer id, String comment, String pmName) {
        this.id = id;
        this.comment = comment;
        this.pmName = pmName;
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

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }
}
