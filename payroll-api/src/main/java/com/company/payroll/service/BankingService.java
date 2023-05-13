package com.company.payroll.service;

import com.company.payroll.model.BankingInfo;
import com.github.pagehelper.PageInfo;

public interface BankingService {

	/**
	 * 
	 * @return
	 */
	PageInfo<BankingInfo> getListByPage(int page, int offset);
	
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
