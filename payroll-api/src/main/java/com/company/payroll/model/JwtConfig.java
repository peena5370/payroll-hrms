package com.company.payroll.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt.config")
public class JwtConfig {
	private String key;
	
	private long ttl;
}
