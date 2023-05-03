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
	public List<Account> getList() {
		return accountMapper.selectList();
	}
	
	@Override
	public Account getById(int aid) {
		return accountMapper.selectByPrimaryKey(aid);
	}
	
	@Override
	public Integer insert(Account row) {
		return accountMapper.insertSelective(row);
	}

	@Override
	public Integer updateListPassword(Account account) {
		return accountMapper.updateByPrimaryKeySelective(account);
	}
	
	@Override
	public Integer update(Account account) {
		return accountMapper.updateByPrimaryKeySelective(account);
	}
	
	@Override
	public Integer delete(int aid) {
		return accountMapper.deleteByPrimaryKey(aid);
	}

	@Override
	public Account getByUsername(String username) {
		return accountMapper.selectByUsername(username);
	}
	
	@Override
	public Integer updateImagePath(Account account) {
		return accountMapper.updateImagePathByUsername(account);
	}

//	@Override
//	public Integer countAccount() {
//		// TODO Auto-generated method stub
//		return accountMapper.countAccount();
//	}

//	@Override
//	public Integer countAccountByStatus() {
//		// TODO Auto-generated method stub
//		return accountMapper.countAccountbyStatus();
//	}
}
