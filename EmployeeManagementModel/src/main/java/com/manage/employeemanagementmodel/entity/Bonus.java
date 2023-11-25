package com.manage.employeemanagementmodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bonus")
public class Bonus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "gratuity", nullable = false)
    private Long gratuity;
    @OneToMany(mappedBy = "bonus")
    @JsonIgnore
    private List<EmployeeBonus> employeeBonuses;

    public Bonus() {
    }

    public Bonus(Integer id, String name, String description, Long gratuity, List<EmployeeBonus> employeeBonuses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gratuity = gratuity;
        this.employeeBonuses = employeeBonuses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGratuity() {
        return gratuity;
    }

    public void setGratuity(Long gratuity) {
        this.gratuity = gratuity;
    }

    public List<EmployeeBonus> getEmployeeBonuses() {
        return employeeBonuses;
    }

    public void setEmployeeBonuses(List<EmployeeBonus> employeeBonuses) {
        this.employeeBonuses = employeeBonuses;
    }
}
