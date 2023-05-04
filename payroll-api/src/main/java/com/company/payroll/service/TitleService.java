package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Title;

public interface TitleService {
	
	/**
	 * 
	 * @return
	 */
	List<Title> getList();
	
	/**
	 * 
	 * @param titleno
	 * @return
	 */
	Title getByTitleno(int titleno);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	Integer insert(Title title);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	Integer update(Title title);
	
	/**
	 * 
	 * @param titleno
	 * @return
	 */
	Integer delete(int titleno);
}
