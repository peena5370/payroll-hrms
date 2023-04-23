package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Resignation;

public interface ResignationService {

	/** 
	 * 
	 * @return
	 * */
	List<Resignation> listResignation();
	
	/** 
	 * 
	 * @return
	 * */
	Resignation getInfoById(int resignid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertResignInfo(Resignation resign);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateResignInfoById(Resignation resign);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateResignStatus(Resignation resign);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteResign(int resignid);
}
