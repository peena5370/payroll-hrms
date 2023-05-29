package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Loan;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @deprecated replaced with {@link #StaffApplicationService} class
 */
@Deprecated
public interface LoanService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Loan> getListByPage(int page, int offset);
	
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
