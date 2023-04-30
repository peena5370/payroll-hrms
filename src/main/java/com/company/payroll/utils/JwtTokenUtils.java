package com.company.payroll.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.company.payroll.model.Account;
import com.company.payroll.model.JwtTokenProperties;
import com.company.payroll.service.AccountService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	private AccountService accountService;
	
	private JwtTokenProperties jwtTokenProperties;
	
	public JwtTokenUtils(JwtTokenProperties jwtTokenProperties, AccountService accountService) {
		this.jwtTokenProperties = jwtTokenProperties;
		this.accountService = accountService;
	}
	/**
	 * Generate token
	 * @param Claims
	 * @return Token
	 */
	public String generateToken(Map<String, Object> claims) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] secretkey = Base64.getEncoder().encode(jwtTokenProperties.getKey().getBytes());
		Key key = new SecretKeySpec(secretkey, signatureAlgorithm.getJcaName());

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder()
							.setIssuedAt(now)
							.signWith(key, signatureAlgorithm)
							.setClaims(claims);
		
		long ttl = jwtTokenProperties.getTtl();
		if(ttl > 0) {
			builder.setExpiration(new Date(nowMillis + ttl));
		}
		
		return builder.compact();
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
	
	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		
		return claims.get("username").toString();
	}
	
	public boolean validateToken(String token) {
		String username = getUserNameFromToken(token);
		Account account = accountService.getByUsername(username);
		
		return username.equals(account.getUsername()) && !validateTokenExpired(token);
	}
	
	private boolean validateTokenExpired(String token) {
		Claims claims = getClaims(token);
		Date date = claims.getExpiration();
		
		return date.before(new Date());
	}
}
