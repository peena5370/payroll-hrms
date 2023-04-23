package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.BankingInfo;

public interface BankingService {

	/** 
	 * 
	 * @return
	 * */
	List<BankingInfo> listBankInfo();
	
	/** 
	 * 
	 * @return
	 * */
	BankingInfo getBankInfoById(int bid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertBankInfo(BankingInfo bank);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateBankInfoById(BankingInfo bank);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteBankInfo(int bid);
}
