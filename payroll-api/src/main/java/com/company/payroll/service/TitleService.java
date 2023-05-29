package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.Title;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @deprecated replaced with {@link #CompanyInfoService} class
 */
@Deprecated
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
	Optional<Title> getByTitleno(int titleno);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	Title insert(Title title);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	Title update(Title title);
	
	/**
	 * 
	 * @param titleno
	 * @return
	 */
	Integer delete(int titleno);
}
