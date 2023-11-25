package com.manage.employeemanagementmodel.entity;

import com.manage.employeemanagementmodel.entity.enums.MailType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mail_config")
public class MailConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "mail_from")
	private String mailFrom;
	@Column(name = "mail_password")
	private String password;
	@Column(name = "mail_host")
	private String host;
	@Column(name = "mail_port")
	private String port;
	@Column(name = "mail_sender_name")
	private String senderName;
	@Column(name = "mail_username")
	private String userName;
	@Column(name = "mail_smtp_auth")
	private boolean smtpAuth;
	@Column(name = "mail_smtp_secured")
	private boolean smtpSecured;
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private MailType type;

	public MailConfig() {
	}

	public MailConfig(Integer id, String mailFrom, String password, String host, String port, String senderName, String userName, boolean smtpAuth, boolean smtpSecured, MailType type) {
		this.id = id;
		this.mailFrom = mailFrom;
		this.password = password;
		this.host = host;
		this.port = port;
		this.senderName = senderName;
		this.userName = userName;
		this.smtpAuth = smtpAuth;
		this.smtpSecured = smtpSecured;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public boolean isSmtpSecured() {
		return smtpSecured;
	}

	public void setSmtpSecured(boolean smtpSecured) {
		this.smtpSecured = smtpSecured;
	}

	public MailType getType() {
		return type;
	}

	public void setType(MailType type) {
		this.type = type;
	}
}
