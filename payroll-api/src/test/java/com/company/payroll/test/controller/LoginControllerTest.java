package com.company.payroll.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.company.payroll.controller.api.LoginController;
import com.company.payroll.service.AccountService;

@WebMvcTest(value=LoginController.class, 
			excludeAutoConfiguration= {SecurityAutoConfiguration.class},
			excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern="com.company.payroll.*.*Filter"))
public class LoginControllerTest extends AbstractControllerTest {

	@InjectMocks
	private LoginController loginController;
	
	@MockBean
	private AccountService accountService;
	
	@BeforeEach
	@Override
	protected void setUp() {
		super.setUp();
	}
	
//	@Test
	void whenLoginSuccess() throws Exception {
		
	}
	
//	@Test
	void whenLoginFailed() throws Exception {
		
	}
	
//	@Test
	void whenLogoutSuccess() throws Exception {
		
	}

}
