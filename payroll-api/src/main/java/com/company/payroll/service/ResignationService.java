package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Resignation;

public interface ResignationService {

	/**
	 * 
	 * @return
	 */
	List<Resignation> getList();
	
	/**
	 * 
	 * @param resignid
	 * @return
	 */
	Resignation getById(int resignid);
	
	/**
	 * 
	 * @param resign
	 * @return
	 */
	Integer insert(Resignation resign);
	
	/**
	 * 
	 * @param resign
	 * @return
	 */
	Integer update(Resignation resign);

	
	/**
	 * 
	 * @param resignid
	 * @return
	 */
	Integer delete(int resignid);
}
