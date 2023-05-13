package com.company.payroll.service;

import com.company.payroll.model.Title;
import com.github.pagehelper.PageInfo;

public interface TitleService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Title> getListByPage(int page, int offset);
	
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
