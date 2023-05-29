package com.company.payroll.service;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.Leave;
import com.company.payroll.model.Loan;
import com.github.pagehelper.PageInfo;

public interface StaffApplicationService {

	/**
	 * 
	 * @param lid
	 * @return
	 */
	Integer deleteLeave(int lid);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Integer deleteLoan(int lid);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	Optional<List<Leave>> findLeaveByEId(int eid);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Optional<Leave> findLeaveById(int lid);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	Optional<List<Loan>> findLoanByEId(int eid);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Optional<Loan> findLoanById(int lid);
	
	/**
	 * 
	 * @param leave
	 * @return
	 */
	Leave insertLeave(Leave leave);
	
	/**
	 * 
	 * @param loan
	 * @return
	 */
	Loan insertLoan(Loan loan);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Leave> listLeave(int page, int offset);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Loan> listLoan(int page, int offset);
	
	/**
	 * 
	 * @param leave
	 * @return
	 */
	Leave updateLeave(Leave leave);
	
	/**
	 * 
	 * @param loan
	 * @return
	 */
	Loan updateLoan(Loan loan);
}
