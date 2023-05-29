package com.company.payroll.service;

import com.company.payroll.model.Employee;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @deprecated replaced with {@link #StaffDetailsService} class
 */
@Deprecated
public interface EmployeeService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Employee> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	Employee getById(int eid);
	
	/**
	 * 
	 * @param employee
	 * @return
	 */
	Integer insert(Employee employee);
	
	/**
	 * 
	 * @param employee
	 * @return
	 */
	Integer update(Employee employee);

	/**
	 * 
	 * @param eid
	 * @return
	 */
	Integer delete(int eid);
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countEmployee();
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countAvailableEmployee();
}
