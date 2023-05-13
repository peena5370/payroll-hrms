package com.company.payroll.service;

import com.company.payroll.model.Salary;
import com.github.pagehelper.PageInfo;

public interface SalaryService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Salary> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param sid
	 * @return
	 */
	Salary getById(int sid);
	
	/**
	 * 
	 * @param salary
	 * @return
	 */
	Integer insert(Salary salary);
	
	/**
	 * 
	 * @param salary
	 * @return
	 */
	Integer update(Salary salary);
	
	/**
	 * 
	 * @param sid
	 * @return
	 */
	Integer delete(int sid);
}
