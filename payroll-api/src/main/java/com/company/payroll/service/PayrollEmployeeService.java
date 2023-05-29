package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.PayrollEmployee;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @deprecated replaced with {@link #StaffPayrollService} class
 *
 */
@Deprecated
public interface PayrollEmployeeService {

	/**
	 * 
	 * @return
	 */
	PageInfo<PayrollEmployee> getListByPage(int page, int offset);

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
