package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Department;

public interface DepartmentService {
	
	/**
	 * 
	 * @return
	 */
	List<Department> getList();
	
	/**
	 * 
	 * @param deptno
	 * @return
	 */
	Department getById(int deptno);
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	Integer insert(Department department);
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	Integer update(Department department);
	
	/**
	 * 
	 * @param deptno
	 * @return
	 */
	Integer delete(int deptno);
}
