package com.company.payroll.service;

import com.company.payroll.model.Manager;
import com.github.pagehelper.PageInfo;

public interface ManagerService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Manager> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param mid
	 * @return
	 */
	Manager getById(int mid);
	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	Integer insert(Manager manager);
	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	Integer update(Manager manager);
	
	/**
	 * 
	 * @param mid
	 * @return
	 */
	Integer delete(int mid);

	/** 
	 * 
	 * @return
	 * */
//	Integer countManager();
	
	/** 
	 * 
	 * @return
	 * */
//	Integer countAvailableManager();
}
