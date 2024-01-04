package com.timesheet.controller;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.RefreshToken;
import com.manage.employeemanagementmodel.exception.EmployeeNotFoundException;
import com.timesheet.configuration.security.CustomUserDetails;
import com.timesheet.configuration.security.jwt.JwtTokenUtil;
import com.timesheet.dto.SystemRequestDto;
import com.timesheet.dto.account.AccountRequestDto;
import com.timesheet.dto.account.AccountResponseDto;
import com.timesheet.dto.RefreshTokenDto;
import com.timesheet.service.AccountService;
import com.timesheet.service.EmployeeService;
import com.timesheet.service.RefreshTokenService;
import com.timesheet.configuration.security.refreshtoken.RefreshTokenUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@SecurityRequirement(name = "bearer-key")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountService accountService;
    private final RefreshTokenUtil refreshTokenUtil;
    private final EmployeeService employeeService;

    public AuthRestController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, AccountService accountService, RefreshTokenUtil refreshTokenUtil, RefreshTokenService refreshTokenService, EmployeeService employeeService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
        this.refreshTokenUtil = refreshTokenUtil;
        this.employeeService = employeeService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Validated AccountRequestDto accountRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountRequestDto.getUsername(), accountRequestDto.getPassword())
            );
            CustomUserDetails account = (CustomUserDetails) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(account);
            Account myAccount = account.getAccount();
            RefreshToken refreshToken = refreshTokenUtil.generateRefreshToken(account);
            myAccount.setRefreshToken(refreshToken);
            accountService.save(myAccount);
            AccountResponseDto accountResponseDto = new AccountResponseDto();
            accountResponseDto.setEmail(account.getUsername());
            accountResponseDto.setEmployeeId(employeeService.getEmployeeId(account.getUsername()));
            accountResponseDto.setAccessToken(accessToken);
            accountResponseDto.setRefreshToken(refreshToken.getToken());
            return ResponseEntity.ok(accountResponseDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("check_system_auth")
    public ResponseEntity<Boolean> checkSystemAuth(@RequestBody @Validated SystemRequestDto systemRequestDto) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeByEmployeeId(systemRequestDto.getEmployeeId());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(), systemRequestDto.getPassword()));
            return ResponseEntity.ok(true);
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("refresh_token")
    public ResponseEntity<?> getRefreshToken(@RequestBody @Validated RefreshTokenDto refreshTokenBody) {
        String requestRefreshToken = refreshTokenBody.getRefreshToken();
        if (!refreshTokenUtil.validateRefreshToken(requestRefreshToken)) {
            Account account = jwtTokenUtil.getAccount(requestRefreshToken);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword())
            );
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            System.out.println(userDetails);
            String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
            AccountResponseDto accountResponseDto = new AccountResponseDto();
            accountResponseDto.setEmail(account.getUsername());
            accountResponseDto.setEmployeeId(employeeService.getEmployeeId(account.getUsername()));
            accountResponseDto.setAccessToken(accessToken);
            accountResponseDto.setRefreshToken(refreshTokenBody.getRefreshToken());
            return ResponseEntity.ok(accountResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
