package com.timesheet.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.timesheet.configuration.security.jwt.JwtTokenFilter;
import com.timesheet.dto.error.ErrorMessage;
import com.timesheet.repository.AccountRepository;
import com.timesheet.service.PermissionService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Date;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        jsr250Enabled = true,
        proxyTargetClass = true
)
public class SecurityConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
    private final AccountRepository accountRepository;
    private final JwtTokenFilter jwtTokenFilter;
    private final PermissionService permissionService;


    public SecurityConfig(AccountRepository accountRepository, JwtTokenFilter jwtTokenFilter, PermissionService permissionService) {
        this.accountRepository = accountRepository;
        this.jwtTokenFilter = jwtTokenFilter;
        this.permissionService = permissionService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(accountRepository);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                                        .name("Authorization")
                        )
                );
    }

    @Bean
    public AuthorizationManager authorizationManager() {
        return new CustomPermissionVoter(permissionService);
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        LOGGER.info("JUMP TO FILTER CHAIN");
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(customizer -> {

        });

        httpSecurity.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/app/login", "/app/refresh_token").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/**").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/api/v1/auth/**").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/v2/api-docs/**").authenticated());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/v3/api-docs/**").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/v3/api-docs.yaml").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/swagger-resources").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/swagger-resources/**").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/configuration/ui").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/configuration/security").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/webjars/**").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/swagger-ui/**").permitAll());
        httpSecurity.authorizeHttpRequests(customizer -> customizer.requestMatchers("/swagger-ui.html").permitAll());

        httpSecurity.authorizeHttpRequests(customizer -> customizer.anyRequest().access(authorizationManager()));
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
