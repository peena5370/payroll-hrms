package com.company.payroll.util;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.payroll.model.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {	
	private SignatureAlgorithm ALG = SignatureAlgorithm.HS256;
	private Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);

	@Autowired
	private JwtConfig jwtTokenProperties;

	/**
	 * Generate token
	 * @param Claims
	 * @return Token
	 */
	public String generateToken(String username, Map<String, Object> claims) {
		Key key = new SecretKeySpec(Base64.getEncoder().encode(jwtTokenProperties.getKey().getBytes()), 
									ALG.getJcaName());
		Instant expiration = issuedAt.plus(jwtTokenProperties.getTtl(), 
											ChronoUnit.SECONDS);

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
				.setSigningKey(Base64.getEncoder().encode(jwtTokenProperties.getKey().getBytes()))
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
	 * Validate token is expired
	 * @param token
	 * @return
	 */
	public boolean isExpired(String token) {
		Claims claims = getClaims(token);
		return claims.getExpiration().after(Date.from(Instant.now()
															 .truncatedTo(ChronoUnit.SECONDS)));
	}
}
