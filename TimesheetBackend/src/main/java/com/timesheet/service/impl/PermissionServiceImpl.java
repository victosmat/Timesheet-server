package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.ApiInfo;
import com.timesheet.repository.ApiInfoRepository;
import com.timesheet.service.PermissionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final ApiInfoRepository apiInfoRepository;

    @Value("${ngrok.baseUrl}")
    private String baseUrlNgrok;

    public PermissionServiceImpl(ApiInfoRepository apiInfoRepository) {
        this.apiInfoRepository = apiInfoRepository;
    }

    @Override
    public String[] getApiPermission(String apiUrl) {
        if (apiUrl.contains(baseUrlNgrok)) apiUrl = apiUrl.replace(baseUrlNgrok, "http://localhost:8081");
        ApiInfo apiInfo = apiInfoRepository.findByApiUrl(apiUrl);
        if (apiInfo == null) return new String[0];
        return apiInfo.getPermissions().trim().split(";");
    }
}
