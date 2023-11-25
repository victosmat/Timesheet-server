package com.manage.employeemanagementmodel.entity;

import com.manage.employeemanagementmodel.entity.enums.RoleProjectType;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_project")
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "role_project_type")
    @Enumerated(EnumType.STRING)
    private RoleProjectType roleProjectType;

    @Column(name = "is_temp")
    private Boolean isTemp;

    public EmployeeProject() {
    }

    public EmployeeProject(Integer id, Employee employee, Project project, RoleProjectType roleProjectType, Boolean isTemp) {
        this.id = id;
        this.employee = employee;
        this.project = project;
        this.roleProjectType = roleProjectType;
        this.isTemp = isTemp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public RoleProjectType getRoleProjectType() {
        return roleProjectType;
    }

    public void setRoleProjectType(RoleProjectType roleProjectType) {
        this.roleProjectType = roleProjectType;
    }

    public Boolean getTemp() {
        return isTemp;
    }

    public void setTemp(Boolean temp) {
        isTemp = temp;
    }
}
