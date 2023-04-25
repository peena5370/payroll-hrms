package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Account;

public interface AccountService {
	
	/**
	 * 
	 * @return
	 */
	List<Account> getList();
	
	/**
	 * 
	 * @param aid
	 * @return
	 */
	Account getById(int aid);
	
	/**
	 * 
	 * @param row
	 * @return
	 */
	Integer insert(Account row);
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	Integer updateListPassword(Account account);
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	Integer update(Account account);

	/**
	 * 
	 * @param aid
	 * @return
	 */
	Integer delete(int aid);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	Account getByUsername(String username);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countAccount();
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countAccountByStatus();
}
