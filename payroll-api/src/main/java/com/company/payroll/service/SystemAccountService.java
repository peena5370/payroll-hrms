package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.Account;
import com.github.pagehelper.PageInfo;

public interface SystemAccountService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Account> list(int page, int offset);
	
	/**
	 * 
	 * @param aid
	 * @return
	 */
	Optional<Account> findById(int aid);
	
	/**
	 * 
	 * @param row
	 * @return
	 */
	Account insert(Account row);
	
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
	Account update(Account account);

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
	Account updateImagePath(Account account);
	
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
