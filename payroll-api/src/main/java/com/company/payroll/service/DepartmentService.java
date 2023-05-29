package com.company.payroll.service;

import com.company.payroll.model.Department;
import com.github.pagehelper.PageInfo;

/**
 *
 * @deprecated replaced with {@link #CompanyInfoService} class
 */
@Deprecated
public interface DepartmentService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Department> getListByPage(int page, int offset);
	
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
