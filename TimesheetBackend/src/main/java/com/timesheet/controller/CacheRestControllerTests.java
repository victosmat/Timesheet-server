package com.timesheet.controller;

import com.timesheet.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caches")
@SecurityRequirement(name = "bearer-key")
public class CacheRestControllerTests {
    private final CacheManager cacheManager;
    private final AccountService accountService;

    public CacheRestControllerTests(CacheManager cacheManager, AccountService accountService) {
        this.cacheManager = cacheManager;
        this.accountService = accountService;
    }

    @GetMapping("get_all")
    public ResponseEntity<?> getAllCaches(@RequestParam("value") String cacheName, @RequestParam("key") String key) {
        Cache cache = cacheManager.getCache(cacheName);
        assert cache != null;
        Object cacheObject = cache.getNativeCache();
        return ResponseEntity.ok(cacheObject);
    }
    @GetMapping("logout")
    public ResponseEntity<?> logout() {
        accountService.logout();
        return ResponseEntity.ok(true);
    }
}
