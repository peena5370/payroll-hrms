package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;
import com.company.payroll.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<Account> listAccount() {
		// TODO Auto-generated method stub
		return accountMapper.selectAccountList();
	}
	
	@Override
	public List<Account> listAccountForEmployee() {
		// TODO Auto-generated method stub
		return accountMapper.selectAccountforEmployee();
	}

	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return accountMapper.selectAccountbyId(id);
	}

	@Override
	public Integer countAccount() {
		// TODO Auto-generated method stub
		return accountMapper.countAccount();
	}

	@Override
	public Integer countAccountByStatus() {
		// TODO Auto-generated method stub
		return accountMapper.countAccountbyStatus();
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return accountMapper.selectAccountbyUsername(username);
	}

	@Override
	public Integer insertAccount(Account acc) {
		// TODO Auto-generated method stub
		return accountMapper.insertAccount(acc);
	}

	@Override
	public Integer updateAccountByAdmin(Account acc) {
		// TODO Auto-generated method stub
		return accountMapper.updatePasswordbyAdmin(acc);
	}

	@Override
	public Integer updatePasswordByManager(Account acc) {
		// TODO Auto-generated method stub
		return accountMapper.updatePasswordbyManager(acc);
	}

	@Override
	public Integer updateAccountStatus(Account acc) {
		// TODO Auto-generated method stub
		return accountMapper.updateAccountStatus(acc);
	}

	@Override
	public Integer deleteAccount(int id) {
		// TODO Auto-generated method stub
		return accountMapper.deleteAccount(id);
	}

}
