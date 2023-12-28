package com.manage.employeemanagementmodel.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.manage.employeemanagementmodel.entity.enums.TimeSheetStatus;
import com.manage.employeemanagementmodel.entity.enums.WorkingType;
import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "note", columnDefinition = "TEXT", nullable = false)
	private String note;
	@Column(name = "date_submit", nullable = false)
	private LocalDate dateSubmit;
	@Column(name = "date_modify")
	private LocalDate dateModify;
	@Column(name = "working_time")
	private Float workingTime;
	@ManyToOne
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	@Column(name = "working_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private WorkingType workingType;
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private TimeSheetStatus status;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Note() {
	}

	public Note(Integer id, String note, LocalDate dateSubmit, LocalDate dateModify, Float workingTime, Task task, WorkingType workingType, Employee employee, TimeSheetStatus status) {
		this.id = id;
		this.note = note;
		this.dateSubmit = dateSubmit;
		this.dateModify = dateModify;
		this.workingTime = workingTime;
		this.task = task;
		this.workingType = workingType;
		this.employee = employee;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getDateSubmit() {
		return dateSubmit;
	}

	public void setDateSubmit(LocalDate dateSubmit) {
		this.dateSubmit = dateSubmit;
	}

	public Float getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(Float workingTime) {
		this.workingTime = workingTime;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public WorkingType getWorkingType() {
		return workingType;
	}

	public void setWorkingType(WorkingType workingType) {
		this.workingType = workingType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TimeSheetStatus getStatus() {
		return status;
	}

	public void setStatus(TimeSheetStatus status) {
		this.status = status;
	}

	public LocalDate getDateModify() {
		return dateModify;
	}

	public void setDateModify(LocalDate dateModify) {
		this.dateModify = dateModify;
	}
}
