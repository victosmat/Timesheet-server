package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "punishments")
public class Punishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "complain", length = 1000)
    private String complain;
    @Column(name = "complain_reply", length = 1000)
    private String complainReply;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punishment_type_id")
    private PunishmentType punishmentType;
    @OneToOne
    @JoinColumn(name = "check_in_id")
    private CheckIn checkIn;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Punishment() {
    }

    public Punishment(Integer id, String complain, String complainReply, LocalDate date, PunishmentType punishmentType, CheckIn checkIn, Boolean isDeleted) {
        this.id = id;
        this.complain = complain;
        this.complainReply = complainReply;
        this.date = date;
        this.punishmentType = punishmentType;
        this.checkIn = checkIn;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getComplainReply() {
        return complainReply;
    }

    public void setComplainReply(String complainReply) {
        this.complainReply = complainReply;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PunishmentType getPunishmentType() {
        return punishmentType;
    }

    public void setPunishmentType(PunishmentType punishmentType) {
        this.punishmentType = punishmentType;
    }

    public CheckIn getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(CheckIn checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
