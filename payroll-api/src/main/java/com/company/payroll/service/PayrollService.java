package com.company.payroll.service;

import java.time.LocalDate;
import java.util.List;

import com.company.payroll.model.PayrollEmployee;

public interface PayrollService {

	/** 
	 * 
	 * @return
	 * */
	List<PayrollEmployee> listPayroll();
	
	/** 
	 * 
	 * @return
	 * */
	List<PayrollEmployee> listPaymentDateAndSapId(int esapid);
	
	
	/** 
	 * 
	 * @return
	 * */
	PayrollEmployee getPayrollById(int prid);
	
	/** 
	 * 
	 * @return
	 * */
	PayrollEmployee getPayslip(int esapid, LocalDate paymentdate);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertPayroll(PayrollEmployee payroll);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updatePayrollById(PayrollEmployee payroll);
}
