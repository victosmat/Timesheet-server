package com.timesheet.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface PermissionService {
    String[] getApiPermission(String apiUrl);
}
