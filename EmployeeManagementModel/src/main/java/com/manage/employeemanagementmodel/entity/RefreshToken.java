package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "token", length = 1000)
    private String token;
    @Column(name = "expired_date")
    private Date expiredDate;

    public RefreshToken() {
    }

    public RefreshToken(Integer id, String token, Date expiredDate) {
        this.id = id;
        this.token = token;
        this.expiredDate = expiredDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public static class NoteComment {
    }
}
