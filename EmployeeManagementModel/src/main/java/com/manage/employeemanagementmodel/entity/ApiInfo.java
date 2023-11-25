package com.manage.employeemanagementmodel.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "api_info")
public class ApiInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "api_url")
    private String apiUrl;
    @Column(name = "api_name")
    private String apiName;
    @Column(name = "description")
    private String description;
    @Column(name = "permissions")
    private String permissions;

    public ApiInfo() {
    }

    public ApiInfo(Integer id, String apiUrl, String apiName, String description, String permissions) {
        this.id = id;
        this.apiUrl = apiUrl;
        this.apiName = apiName;
        this.description = description;
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
