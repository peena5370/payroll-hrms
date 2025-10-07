package com.company.payroll.security.service;

import com.company.payroll.user.dto.JwkKey;
import com.company.payroll.security.util.JwkKeyLocator;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JwtUserVerificationService {

    public Optional<Jws<Claims>> verifyToken(String rawToken, List<JwkKey> cachedKeys) {
        Locator<Key> keyLocator = new JwkKeyLocator(cachedKeys);

        try {
            JwtParser parser = Jwts.parser()
                    .keyLocator(keyLocator)
                    .requireIssuer("https://localhost:8080/user-service.com")
                    .requireAudience("hrms-service")
                    .build();

            Jws<Claims> jwsClaims = parser.parseSignedClaims(rawToken);

            return Optional.of(jwsClaims);

        } catch (Exception e) {
            log.error("JWT Validation Failed: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
