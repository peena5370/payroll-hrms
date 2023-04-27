package com.company.payroll;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootPayrollApplication.class)
class SpringBootPayrollApplicationTests {
	
	@Test
	@DisplayName("JUnit test for starts application")
	void contextLoads() {
	}

}
