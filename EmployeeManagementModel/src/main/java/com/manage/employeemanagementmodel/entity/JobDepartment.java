package com.manage.employeemanagementmodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "job_department")
public class JobDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "job_department", nullable = false, length = 50)
    private String jobDepartment;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Column(name = "salary_range", nullable = false, length = 50)
    private long salaryRange;

    @OneToMany(mappedBy = "jobDepartment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

    public JobDepartment() {
    }

    public JobDepartment(Integer id, String jobDepartment, String name, String description, long salaryRange, List<Employee> employees) {
        this.id = id;
        this.jobDepartment = jobDepartment;
        this.name = name;
        this.description = description;
        this.salaryRange = salaryRange;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobDepartment() {
        return jobDepartment;
    }

    public void setJobDepartment(String jobDepartment) {
        this.jobDepartment = jobDepartment;
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

    public long getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(long salaryRange) {
        this.salaryRange = salaryRange;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
