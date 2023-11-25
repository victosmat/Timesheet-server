package com.timesheet.configuration.security.refreshtoken;

import com.manage.employeemanagementmodel.entity.Account;
import com.manage.employeemanagementmodel.entity.RefreshToken;
import com.timesheet.configuration.security.CustomUserDetails;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefreshTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTokenUtil.class);
    @Value("${refreshtoken.expiration}")
    private long REFRESH_TOKEN_EXPIRED_DATE;
    @Value("${refreshtoken.secret}")
    private String REFRESH_SECRET_KEY;

    public RefreshToken generateRefreshToken(CustomUserDetails account) {
        String refreshTokenString = Jwts.builder()
                .setSubject(String.format("%s", account.getUsername()))
                .claim("roles", account.getAuthorities().toString())
                .setIssuer("TimesheetWebapp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRED_DATE))
                .signWith(SignatureAlgorithm.HS512, REFRESH_SECRET_KEY)
                .compact();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRED_DATE));
        return refreshToken;
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser().setSigningKey(REFRESH_SECRET_KEY).parseClaimsJws(token);
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
}
