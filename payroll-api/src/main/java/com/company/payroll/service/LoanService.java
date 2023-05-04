package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Loan;

public interface LoanService {
	
	/**
	 * 
	 * @return
	 */
	List<Loan> getList();
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	List<Loan> getListByEId(int eid);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Loan getById(int lid);
	
	/**
	 * 
	 * @param loan
	 * @return
	 */
	Integer insert(Loan loan);
	
	/**
	 * 
	 * @param loan
	 * @return
	 */
	Integer update(Loan loan);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Integer delete(int lid);
}
