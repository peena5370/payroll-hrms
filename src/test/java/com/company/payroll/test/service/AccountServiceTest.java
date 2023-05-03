package com.company.payroll.test.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.company.payroll.service.AccountService;

public class AccountServiceTest extends AbstractServiceTest {
	
	@Mock
	private AccountService accountService;

	@Override
	protected void createWhenSuccess() {
		// TODO Auto-generated method stub
		super.createWhenSuccess();
	}

	@Override
	protected void createWhenFailed() {
		// TODO Auto-generated method stub
		super.createWhenFailed();
	}

	@Override
	protected void queryAll() {
		// TODO Auto-generated method stub
		super.queryAll();
	}

	@Override
	protected void queryById() {
		// TODO Auto-generated method stub
		super.queryById();
	}
	
	@Test
	void queryByUsername() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateWhenSuccess() {
		// TODO Auto-generated method stub
		super.updateWhenSuccess();
	}

	@Override
	protected void updateWhenFailed() {
		// TODO Auto-generated method stub
		super.updateWhenFailed();
	}
	
	@Test
	void updateListPasswordWhenSuccess() {
		// TODO Auto-generated method stub
	}
	
	@Test
	void updateListPasswordWhenFailed() {
		// TODO Auto-generated method stub
	}
	
	@Test
	void updateImagePathWhenSuccess() {
		// TODO Auto-generated method stub
	}
	
	@Test
	void updateImagePathWhenFailed() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void deleteWhenSuccess() {
		// TODO Auto-generated method stub
		super.deleteWhenSuccess();
	}

	@Override
	protected void deleteWhenFailed() {
		// TODO Auto-generated method stub
		super.deleteWhenFailed();
	}
	

}
