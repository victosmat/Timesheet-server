package com.timesheet.configuration.security;

import com.timesheet.service.PermissionService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CustomPermissionVoter implements AuthorizationManager<RequestAuthorizationContext> {
    private final PermissionService permissionService;

    public CustomPermissionVoter(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        String[] permissions = permissionService.getApiPermission(object.getRequest().getRequestURL().toString());
        for(String permission : permissions) {
            System.out.println("Permission: " + permission);
        }
        return new AuthoritiesAuthorizationManager().check(authentication, List.of(permissions));
    }
}
