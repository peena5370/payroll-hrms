package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.Department;
import com.company.payroll.model.Title;
import com.github.pagehelper.PageInfo;

public interface CompanyInfoService {
	
	/**
	 * 
	 * @param deptno
	 * @return
	 */
	Integer deleteDepartment(int deptno);
	
	/**
	 * 
	 * @param titleno
	 * @return
	 */
	Integer deleteTitle(int titleno);
	
	/**
	 * 
	 * @param deptno
	 * @return
	 */
	Optional<Department> findDepartmentById(int deptno);
	
	/**
	 * 
	 * @param titleno
	 * @return
	 */
	Optional<Title> findTitleById(int titleno);
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	Department insertDepartment(Department department);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	Title insertTitle(Title title);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Department> listDepartment(int page, int offset);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Title> listTitle(int page, int offset);
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	Department updateDepartment(Department department);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	Title updateTitle(Title title);
}
