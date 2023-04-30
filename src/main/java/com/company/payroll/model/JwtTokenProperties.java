package com.company.payroll.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt.config")
public class JwtTokenProperties {
	@Value("${jwt.config.id}")
	private String id;
	@Value("${jwt.config.key}")
	private String key;
	@Value("${jwt.config.subject}")
	private String subject;
	@Value("${jwt.config.issuer}")
	private String issuer;
	@Value("${jwt.config.ttl}")
	private long ttl;
}
