package com.company.payroll.test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.payroll.service.AccountService;
import com.company.payroll.service.impl.AccountServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=AccountServiceImpl.class)
class AccountServiceTest {
	
	@Autowired
	private AccountService accountService;
	
	@Test
	void testLoadAccountService() {
		assertNotNull(accountService.getList());
	}
}
