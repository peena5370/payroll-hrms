package com.company.payroll.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;
import com.company.payroll.service.SystemAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SystemAccountServiceImpl implements SystemAccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public PageInfo<Account> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Account> list = accountMapper.selectList();
		return new PageInfo<Account>(list);
	}
	
	@Override
	public Optional<Account> findById(int aid) {
		return Optional.ofNullable(accountMapper.selectByPrimaryKey(aid));
	}
	
	@Override
	public Account insert(Account row) {
		accountMapper.insertSelective(row);
		return row;
	}

	@Override
	public Integer updateListPassword(Account account) {
		return accountMapper.updateByPrimaryKeySelective(account);
	}
	
	@Override
	public Account update(Account account) {
		accountMapper.updateByPrimaryKeySelective(account);
		return account;
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
	public Account updateImagePath(Account account) {
		accountMapper.updateImagePathByUsername(account);
		return account;
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
