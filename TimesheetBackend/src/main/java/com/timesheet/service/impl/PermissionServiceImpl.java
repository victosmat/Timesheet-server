package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.ApiInfo;
import com.timesheet.repository.ApiInfoRepository;
import com.timesheet.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    private final ApiInfoRepository apiInfoRepository;

    public PermissionServiceImpl(ApiInfoRepository apiInfoRepository) {
        this.apiInfoRepository = apiInfoRepository;
    }

    @Override
    public String[] getApiPermission(String apiUrl) {
        ApiInfo apiInfo = apiInfoRepository.findByApiUrl(apiUrl);
        if(apiInfo == null) return new String[0];
        return apiInfo.getPermissions().trim().split(";");
    }
}
