package com.company.payroll.service;

import java.security.NoSuchAlgorithmException;
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
	 * @throws NoSuchAlgorithmException 
	 */
	Account insert(Account row) throws NoSuchAlgorithmException;
	
	/**
	 * 
	 * @param account
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	Account updateListPassword(Account account) throws NoSuchAlgorithmException;
	
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
