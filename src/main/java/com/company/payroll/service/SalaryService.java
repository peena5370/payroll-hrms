package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Salary;

public interface SalaryService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Salary> listSalary();
	
	/** 
	 * 
	 * @return
	 * */
	Salary getInfoBySapId(int esapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertSalary(Salary salary);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateSalary(Salary salary);
}
