package com.company.payroll.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.payroll.util.SnowFlakeIdGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class SnowFlakeIdGeneratorWorkerConfig {
	
	@Value("${util.worker-id}")
	private String workerId;

	@Value("${util.datacenter-id}")
	private String datacenterId;
	
	@Bean
	SnowFlakeIdGenerator createSnowFlakeIdGenerator() {
        return new SnowFlakeIdGenerator(Long.parseLong(workerId), Long.parseLong(datacenterId));
	}
}
