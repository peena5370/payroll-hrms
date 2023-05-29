package com.company.payroll.service;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.model.PayrollManager;
import com.github.pagehelper.PageInfo;

public interface StaffPayrollService {
	/**
	 * 
	 * @param prid
	 * @return
	 */
	Integer deletePayrollEmployee(int prid);
	
	/**
	 * 
	 * @param prMgrId
	 * @return
	 */
	Integer deletePayrollManager(int prMgrId);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	Optional<List<PayrollEmployee>> findPayrollEmployeeByEId(int eid);
	
	/**
	 * 
	 * @param prid
	 * @return
	 */
	Optional<PayrollEmployee> findPayrollEmployeeById(int prid);
	
	/**
	 * 
	 * @param prMgrId
	 * @return
	 */
	Optional<PayrollManager> findPayrollManagerById(int prMgrId);
	
	/**
	 * 
	 * @param mid
	 * @return
	 */
	Optional<List<PayrollManager>> findPayrollManagerByMId(int mid);
	
	/**
	 * 
	 * @param payroll
	 * @return
	 */
	PayrollEmployee insertPayrollEmployee(PayrollEmployee payroll);
	
	/**
	 * 
	 * @param payrollManager
	 * @return
	 */
	PayrollManager insertPayrollManager(PayrollManager payrollManager);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<PayrollEmployee> listPayrollEmployee(int page, int offset);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<PayrollManager> listPayrollManager(int page, int offset);
	
	/**
	 * 
	 * @param payroll
	 * @return
	 */
	PayrollEmployee updatePayrollEmployee(PayrollEmployee payroll);
	
	/**
	 * 
	 * @param payrollManager
	 * @return
	 */
	PayrollManager updatePayrollManager(PayrollManager payrollManager);
}
