package com.timesheet.configuration.security;

import com.timesheet.service.PermissionService;
import org.springframework.security.authorization.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class CustomPermissionVoter implements AuthorizationManager<RequestAuthorizationContext> {
    private final PermissionService permissionService;
    private final Logger log = Logger.getLogger(CustomPermissionVoter.class.getName());

    public CustomPermissionVoter(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        String[] permissions = permissionService.getApiPermission(object.getRequest().getRequestURL().toString());
        for(String permission : permissions) log.info("Permission: " + permission);
        return new AuthoritiesAuthorizationManager().check(authentication, List.of(permissions));
    }
}
