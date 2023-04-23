package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Department;

public interface DepartmentService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Department> listBaseDepartment();
	
	/** 
	 * 
	 * @return
	 * */
	Department getBaseDepartmentById(int deptno);
	
	/** 
	 * 
	 * @return
	 * */
	List<Department> listDepartment();
	
	/** 
	 * 
	 * @return
	 * */
	List<Department> listDeptNoAndDeptName();
	
	/** 
	 * 
	 * @return
	 * */
	Integer countDepartment();
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertDepartment(Department dept);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateDepartment(Department dept);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteDepartment(int deptno);
}
