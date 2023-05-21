package com.company.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootPayrollApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootPayrollApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPayrollApplication.class, args);
		log.info("logger info");
		log.warn("logger warn");
		log.error("logger error");
	}
}
