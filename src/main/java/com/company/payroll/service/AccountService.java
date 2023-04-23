package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Account;

public interface AccountService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Account> listAccount();
	
	/** 
	 * 
	 * @return
	 * */
	List<Account> listAccountForEmployee();
	
	/** 
	 * 
	 * @return
	 * */
	Account getAccountById(int id);
	
	/** 
	 * 
	 * @return
	 * */
	Integer countAccount();
	
	/** 
	 * 
	 * @return
	 * */
	Integer countAccountByStatus();
	
	/** 
	 * 
	 * @return
	 * */
	Account getAccountByUsername(String username);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertAccount(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateAccountByAdmin(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updatePasswordByManager(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateAccountStatus(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteAccount(int id);
}
