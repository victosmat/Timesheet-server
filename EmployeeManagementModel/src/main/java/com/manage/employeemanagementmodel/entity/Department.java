package com.manage.employeemanagementmodel.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "department")

public class Department implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employees;

	public Department() {
	}

	public Department(Integer id, String name, List<Employee> employees) {
		this.id = id;
		this.name = name;
		this.employees = employees;
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

	public List<Employee> getEmployees(){
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
