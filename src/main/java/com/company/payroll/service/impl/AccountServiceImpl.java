package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;
import com.company.payroll.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountMapper accountMapper;
	
	public AccountServiceImpl(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public List<Account> getList() {
		return accountMapper.selectList();
	}
	
	@Override
	public Account getById(int aid) {
		return accountMapper.selectByPrimaryKey(aid);
	}
//
//	@Override
//	public Integer countAccount() {
//		// TODO Auto-generated method stub
//		return accountMapper.countAccount();
//	}
//
//	@Override
//	public Integer countAccountByStatus() {
//		// TODO Auto-generated method stub
//		return accountMapper.countAccountbyStatus();
//	}
//
//	@Override
//	public Account getAccountByUsername(String username) {
//		// TODO Auto-generated method stub
//		return accountMapper.selectAccountbyUsername(username);
//	}
//
	@Override
	public Integer insert(Account row) {
		// TODO Auto-generated method stub
		return accountMapper.insertSelective(row);
	}
//
//	@Override
//	public Integer updateAccountByAdmin(Account acc) {
//		// TODO Auto-generated method stub
//		return accountMapper.updatePasswordbyAdmin(acc);
//	}
//
//	@Override
//	public Integer updatePasswordByManager(Account acc) {
//		// TODO Auto-generated method stub
//		return accountMapper.updatePasswordbyManager(acc);
//	}
//
//	@Override
//	public Integer updateAccountStatus(Account acc) {
//		// TODO Auto-generated method stub
//		return accountMapper.updateAccountStatus(acc);
//	}
//
//	@Override
//	public Integer deleteAccount(int id) {
//		// TODO Auto-generated method stub
//		return accountMapper.deleteAccount(id);
//	}

}
