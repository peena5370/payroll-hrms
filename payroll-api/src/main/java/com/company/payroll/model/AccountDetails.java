package com.company.payroll.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountDetails implements UserDetails {

	private static final long serialVersionUID = 9130810278327198358L;
	
	private Account account;
	
	public AccountDetails(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		if(account.getAccountStatus()==(byte) 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isAccountNonLocked() {
		if(account.getAccountStatus()==(byte) 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		if(account.getAccountStatus()==(byte) 1) {
			return true;
		} else {
			return false;
		}
	}

}
