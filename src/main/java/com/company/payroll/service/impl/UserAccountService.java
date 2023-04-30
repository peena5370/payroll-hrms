package com.company.payroll.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.AccountMapper;
import com.company.payroll.model.Account;

@Service
public class UserAccountService implements UserDetailsService {

	private AccountMapper accountMapper;
	
	public UserAccountService(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountMapper.selectByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("Username: "+ username + " is not exist.");
		}
		
		return User
				.withUsername(account.getUsername())
				.password(account.getPassword())
				.roles(account.getRoles())
				.build();
	}
}
