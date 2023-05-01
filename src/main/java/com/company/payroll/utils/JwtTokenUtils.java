package com.company.payroll.utils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.company.payroll.model.JwtTokenProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	
	private final SignatureAlgorithm ALG = SignatureAlgorithm.HS256;
	private Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
	private final byte[] SECRET_KEY;
	private long TTL;
	
	public JwtTokenUtils(JwtTokenProperties jwtTokenProperties) {
		this.SECRET_KEY = Base64.getEncoder().encode(jwtTokenProperties.getKey().getBytes());
		this.TTL = jwtTokenProperties.getTtl();
	}
	/**
	 * Generate token
	 * @param Claims
	 * @return Token
	 */
	public String generateToken(String username, Map<String, Object> claims) {
		Key key = new SecretKeySpec(SECRET_KEY, ALG.getJcaName());
		Instant expiration = issuedAt.plus(TTL, ChronoUnit.SECONDS);

		return Jwts.builder()
					.setClaims(claims)
					.setSubject(username)
					.setExpiration(Date.from(expiration))
					.signWith(key, ALG)
					.compact();
	}
	
	/**
	 * Get claims from token
	 * @param token
	 * @return Claims
	 */
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	/**
	 * Get subject username
	 * @param token
	 * @return
	 */
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		
		return claims.getSubject();
	}
	
	/**
	 * validate token
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		if(getUsername(token) != null && isExpired(token)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public boolean isExpired(String token) {
		Claims claims = getClaims(token);
		return claims.getExpiration().after(Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS)));
	}
}
