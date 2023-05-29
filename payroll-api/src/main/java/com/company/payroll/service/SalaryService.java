package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.Salary;
import com.github.pagehelper.PageInfo;

public interface SalaryService {
	
	/**
	 * 
	 * @param sid
	 * @return
	 */
	Optional<Salary> findById(int sid);
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Salary> list(int page, int offset);
	
	/**
	 * 
	 * @param salary
	 * @return
	 */
	Salary update(Salary salary);
}
