package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.PayrollManager;
import com.github.pagehelper.PageInfo;

public interface PayrollManagerService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<PayrollManager> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param mid
	 * @return
	 */
	List<PayrollManager> getListByMId(int mid);
	
	/**
	 * 
	 * @param prMgrId
	 * @return
	 */
	PayrollManager getById(int prMgrId);
	
	/**
	 * 
	 * @param payrollManager
	 * @return
	 */
	Integer insert(PayrollManager payrollManager);
	
	/**
	 * 
	 * @param payrollManager
	 * @return
	 */
	Integer update(PayrollManager payrollManager);
	
	/**
	 * 
	 * @param prMgrId
	 * @return
	 */
	Integer delete(int prMgrId);
}
