package com.company.payroll.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.company.payroll.model.JwtTokenProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	
	private JwtTokenProperties jwtTokenProperties;
	
	public JwtTokenUtils(JwtTokenProperties jwtTokenProperties) {
		this.jwtTokenProperties = jwtTokenProperties;
	}
	
	/**
	 * generate jwt
	 * @param claims
	 * @return
	 */
	public String createJwt(Map<String, Object> claims) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] secretkey = Base64.getEncoder().encode(jwtTokenProperties.getKey().getBytes());
		Key key = new SecretKeySpec(secretkey, signatureAlgorithm.getJcaName());
		
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder()
				.setId(jwtTokenProperties.getId())
				.setSubject(jwtTokenProperties.getSubject())
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
	 * decode jwt
	 * @param jwtStr
	 * @return
	 */
	public Claims parseJwt(String jwtStr) {
		return Jwts.parserBuilder()
				.setSigningKey(Base64.getEncoder().encode(jwtTokenProperties.getKey().getBytes()))
				.build()
				.parseClaimsJws(jwtStr)
				.getBody();
	}
}
