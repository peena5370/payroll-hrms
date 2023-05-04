package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Salary;

public interface SalaryService {
	
	/**
	 * 
	 * @return
	 */
	List<Salary> getList();
	
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
