package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Account;

public interface AccountService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Account> getList();
	
	/** 
	 * 
	 * @return
	 * */
	Account getById(int aid);
	
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
	
	/** 
	 * 
	 * @return
	 * */
//	Account getAccountByUsername(String username);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insert(Account row);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer updateAccountByAdmin(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer updatePasswordByManager(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer updateAccountStatus(Account acc);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer deleteAccount(int id);
}
