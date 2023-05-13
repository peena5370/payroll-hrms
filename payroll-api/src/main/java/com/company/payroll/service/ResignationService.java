package com.company.payroll.service;

import com.company.payroll.model.Resignation;
import com.github.pagehelper.PageInfo;

public interface ResignationService {

	/**
	 * 
	 * @return
	 */
	PageInfo<Resignation> getListByPage(int page, int offset);
	
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
