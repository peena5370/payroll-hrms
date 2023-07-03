package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.Department;
import com.company.payroll.model.Title;
import com.github.pagehelper.PageInfo;

public interface CompanyInfoService {
	
	/**
	 * 
	 * @param deptNo dept number
	 * @return Deleted row
	 */
	Integer deleteDepartment(Integer deptNo);
	
	/**
	 * 
	 * @param titleNo title number
	 * @return Deleted row
	 */
	Integer deleteTitle(Integer titleNo);
	
	/**
	 * 
	 * @param deptNo dept number
	 * @return {@link Department} object
	 */
	Optional<Department> findDepartmentById(Integer deptNo);
	
	/**
	 * 
	 * @param titleNo title number
	 * @return {@link Title} object
	 */
	Optional<Title> findTitleById(Integer titleNo);
	
	/**
	 * 
	 * @param department {@link Department} object
	 * @return After inserted {@link Department} object
	 */
	Department insertDepartment(Department department);
	
	/**
	 * 
	 * @param title {@link Title} object
	 * @return After inserted {@link Title} object
	 */
	Title insertTitle(Title title);
	
	/**
	 * 
	 * @param page page number
	 * @param offset page data limit
	 * @return List of Department
	 */
	PageInfo<Department> listDepartment(int page, int offset);
	
	/**
	 * 
	 * @param page page number
	 * @param offset page data limit
	 * @return List of Title
	 */
	PageInfo<Title> listTitle(int page, int offset);
	
	/**
	 * 
	 * @param department {@link Department} object
	 * @return After updated {@link Department} object
	 */
	Department updateDepartment(Department department);
	
	/**
	 * 
	 * @param title {@link Title} object
	 * @return After updated {@link Title} object
	 */
	Title updateTitle(Title title);
}
