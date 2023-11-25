package com.timesheet.configuration.security.jwt;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.Role;
import com.timesheet.configuration.security.CustomUserDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    @Value("${jwt.expiration}")
    private long TOKEN_EXPIRED_DAY;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /*
        HEADER:ALGORITHM & TOKEN TYPE
        {
          "alg": "HS512",
          "typ": "JWT"
        }
        PAYLOAD:DATA
        {
          "sub": "1234567890",
          "name": "John Doe",
          "iat": 1516239022
        }
        VERIFY SIGNATURE
        HMACSHA512(
          base64UrlEncode(header) + "." +
          base64UrlEncode(payload),
          your-512-bit-secret
        )
    */
    public String generateAccessToken(CustomUserDetails account) {
        return Jwts.builder()
                .setSubject(String.format("%s", account.getUsername()))
                .claim("roles", account.getAuthorities().toString())
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRED_DAY))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String generateAccessTokenUsingField(Account account) {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        account.getRoles().forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });
        return Jwts.builder()
                .setSubject(String.format("%s", account.getUsername()))
                .claim("roles", roles.toString())
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRED_DAY))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.split(" ")[1].trim();
    }

    public void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Account getAccount(String token) {
        Claims claims = parseClaims(token);

        String claimRoles = ((String) claims.get("roles")).replace("[", "").replace("]", "");
        String[] roleNames = claimRoles.split(", ");

        List<Role> roles = Arrays.stream(roleNames)
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .collect(Collectors.toList());

        String subject = (String) claims.get(Claims.SUBJECT);

        Account account = new Account();
        account.setRoles(roles);
        account.setUsername(subject);

        return account;
    }


    public UserDetails getUserDetails(String token) {
        Account account = getAccount(token);
        return new CustomUserDetails(account);
    }
}
