package com.company.payroll.service;

import com.company.payroll.model.Account;
import com.github.pagehelper.PageInfo;

public interface AccountService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Account> getListByPage(int page, int offset);
	
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
	 * @param account
	 * @return
	 */
	Integer updateImagePath(Account account);
	
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
