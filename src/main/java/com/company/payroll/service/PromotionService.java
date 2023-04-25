package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Promotion;

public interface PromotionService {
	
	/**
	 * 
	 * @return
	 */
	List<Promotion> getList();
	
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
