package com.manage.employeemanagementmodel.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_details")
public class JobDetails implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "job_id")
	private String jobId;
	@Column(name = "job_name")
	private String jobName;
	@Column(name = "cron_time")
	private String cronTime;
	@Column(name = "job_description")
	private String jobDescription;
	@Column(name = "template_name")
	private String templateName;
	@Column(name = "service")
	private String service;

	public JobDetails() {
	}

	public JobDetails(Integer id, String jobId, String jobName, String cronTime, String jobDescription, String templateName, String service) {
		this.id = id;
		this.jobId = jobId;
		this.jobName = jobName;
		this.cronTime = cronTime;
		this.jobDescription = jobDescription;
		this.templateName = templateName;
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCronTime() {
		return cronTime;
	}

	public void setCronTime(String cronTime) {
		this.cronTime = cronTime;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
}
