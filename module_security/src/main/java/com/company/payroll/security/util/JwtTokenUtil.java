package com.company.payroll.security.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenUtil {

	@Value("${jwt.config.key}")
	private String keyString;
	@Value("${jwt.config.ttl}")
	private long ttl;

	private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(keyString.getBytes());
    }

	/**
	 * Generate authorization token
     * @param username token subject
	 * @param claims Map of claims which stored inside the token
	 * @return authorization token
	 */
	public String generateToken(String username, Map<String, Object> claims) {
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(this.ttl, ChronoUnit.SECONDS);

		return Jwts.builder()
                .claims(claims)
                .subject(username)
                .expiration(Date.from(expiration))
                .signWith(this.key)
                .compact();
	}

	/**
	 * Get claims from token
	 * @param token authorization token
	 * @return Claims
	 */
	public Claims getClaims(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token);

            return jws.getPayload();
        } catch (ExpiredJwtException e) {
            log.warn("Token has expired: {}", e.getMessage());
            return e.getClaims();
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            log.error("Token invalid and encountered exception: {}", e.getMessage());
            return null;
        }
	}

	/**
	 * Get token subject
	 * @param token authorization token
	 * @return return token username
	 */
	public String getUsername(String token) {
		Claims claims = getClaims(token);

		return (claims != null) ? claims.getSubject() : null;
	}

    /**
     * Validate token expiry
     * @param token authorization token
     * @return validate status
     */
	public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (Exception e) {
            log.error("Token validation failed: {}", e.getMessage());

            return false;
        }
	}
}
