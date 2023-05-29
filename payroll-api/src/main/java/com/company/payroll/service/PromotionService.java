package com.company.payroll.service;

import com.company.payroll.model.Promotion;
import com.github.pagehelper.PageInfo;

/**
 * @deprecated replace with {@link #StaffMiscellaneousService} class
 */
@Deprecated
public interface PromotionService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Promotion> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	Promotion getById(int pid);
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	Integer insert(Promotion promotion);
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	Integer update(Promotion promotion);
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	Integer delete(int pid);
}
