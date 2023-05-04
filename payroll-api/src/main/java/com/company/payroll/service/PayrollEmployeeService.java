package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.PayrollEmployee;

public interface PayrollEmployeeService {

	/**
	 * 
	 * @return
	 */
	List<PayrollEmployee> getList();

	/**
	 * 
	 * @param eid
	 * @return
	 */
	List<PayrollEmployee> getListByEId(int eid);
		
	/**
	 * 
	 * @param prid
	 * @return
	 */
	PayrollEmployee getById(int prid);
	
	/**
	 * 
	 * @param payroll
	 * @return
	 */
	Integer insert(PayrollEmployee payroll);
	
	/**
	 * 
	 * @param payroll
	 * @return
	 */
	Integer update(PayrollEmployee payroll);
	
	/**
	 * 
	 * @param prid
	 * @return
	 */
	Integer delete(int prid);	
}
