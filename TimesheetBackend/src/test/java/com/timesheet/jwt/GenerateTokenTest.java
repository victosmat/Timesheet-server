package com.timesheet.jwt;

import com.timesheet.configuration.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateTokenTest {
    @Test
    public void generateAccessToken() {
        System.out.println("Token expired day: " + 0 );
        System.out.println("Secret key: " + "username" );
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("PM"));
        authorities.add(new SimpleGrantedAuthority("STAFF"));
        String accessToken = Jwts.builder()
                .setSubject(String.format("%s", "pm@ncc.asia"))
                .claim("roles", authorities)
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "username")
                .compact();
        System.out.println(accessToken);
    }
}
