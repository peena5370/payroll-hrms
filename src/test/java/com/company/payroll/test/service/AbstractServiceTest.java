package com.company.payroll.test.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(MockitoExtension.class)
public abstract class AbstractServiceTest {

	protected void createWhenSuccess() {}
	
	protected void createWhenFailed() {}
	
	protected void queryAll() {}
	
	protected void queryById() {}
	
	protected void updateWhenSuccess() {}
	
	protected void updateWhenFailed() {}
	
	protected void deleteWhenSuccess() {}
	
	protected void deleteWhenFailed() {}
}
