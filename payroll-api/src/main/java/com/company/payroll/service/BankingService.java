package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.BankingInfo;
import com.github.pagehelper.PageInfo;

public interface BankingService {

	/**
	 * 
	 * @param bid
	 * @return
	 */
	Optional<BankingInfo> findById(int bid);
	
	/**
	 * 
	 * @return
	 */
	PageInfo<BankingInfo> list(int page, int offset);

	/**
	 * 
	 * @param bank
	 * @return
	 */
	BankingInfo update(BankingInfo bank);

}
