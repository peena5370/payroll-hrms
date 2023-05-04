package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.BankingInfo;

public interface BankingService {

	/**
	 * 
	 * @return
	 */
	List<BankingInfo> getList();
	
	/**
	 * 
	 * @param bid
	 * @return
	 */
	BankingInfo getById(int bid);
	
	/**
	 * 
	 * @param bank
	 * @return
	 */
	Integer insert(BankingInfo bank);
	
	/**
	 * 
	 * @param bank
	 * @return
	 */
	Integer updateById(BankingInfo bank);
	
	/**
	 * 
	 * @param bid
	 * @return
	 */
	Integer delete(int bid);
}
