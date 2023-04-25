package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Leave;

public interface LeaveService {
	
	/**
	 * 
	 * @return
	 */
	List<Leave> getList();
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	List<Leave> getListByEId(int eid);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Leave getById(int lid);

	/**
	 * 
	 * @param leave
	 * @return
	 */
	Integer insert(Leave leave);
	
	/**
	 * 
	 * @param leave
	 * @return
	 */
	Integer update(Leave leave);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Integer delete(int lid);
}
