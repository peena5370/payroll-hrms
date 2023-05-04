package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Manager;

public interface ManagerService {
	
	/**
	 * 
	 * @return
	 */
	List<Manager> getList();
	
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
