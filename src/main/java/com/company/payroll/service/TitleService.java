package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Title;

public interface TitleService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Title> listTitle();
	
	/** 
	 * 
	 * @return
	 * */
	List<Title> listTitlenoAndName();
	
	/** 
	 * 
	 * @return
	 * */
	Title getInfoByTitleno(int titleno);
	
	/** 
	 * 
	 * @return
	 * */
	Integer countTitle();
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertTitle(String titlename, String titledesc);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateTitle(Title title);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteTitle(int titleno);
}
