package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "note_comment")
public class NoteComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_readed")
    private Boolean isReaded;

    @OneToOne
    @JoinColumn(name = "note_id")
    private Note note;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public NoteComment() {
    }

    public NoteComment(Integer id, String comment, Boolean isReaded, Note note, Employee employee) {
        this.id = id;
        this.comment = comment;
        this.isReaded = isReaded;
        this.note = note;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Note getNote() {
        return note;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boolean getReaded() {
        return isReaded;
    }

    public void setReaded(Boolean readed) {
        isReaded = readed;
    }
}
