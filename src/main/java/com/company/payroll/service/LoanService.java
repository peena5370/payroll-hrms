package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Loan;

public interface LoanService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Loan> listLoan();
	
	/** 
	 * 
	 * @return
	 * */
	List<Loan> listLoanBySapId(int esapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertLoan(Loan loan);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateLoan(Loan loan);
}
