package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Employee;

public interface EmployeeService {
	
	/**
	 * 
	 * @return
	 */
	List<Employee> getList();
	
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
