package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.ApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiInfoRepository extends JpaRepository<ApiInfo, Integer> {
    ApiInfo findByApiUrl(String apiUrl);
}
