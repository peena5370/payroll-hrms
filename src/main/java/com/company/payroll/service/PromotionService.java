package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Promotion;

public interface PromotionService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Promotion> listPromotion();
	
	/** 
	 * 
	 * @return
	 * */
	Promotion getPromotionById(int pid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertPromotion(Promotion promotion);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updatePromotion(Promotion promotion);
}
