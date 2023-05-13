package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Training;
import com.github.pagehelper.PageInfo;

public interface TrainingService {

	/**
	 * 
	 * @return
	 */
	PageInfo<Training> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	List<Training> getListByEId(int eId);
	
	/**
	 * 
	 * @param tid
	 * @return
	 */
	Training getById(int tId);
	
	/**
	 * 
	 * @param training
	 * @return
	 */
	Integer insert(Training training);
	
	/**
	 * 
	 * @param training
	 * @return
	 */
	Integer update(Training training);
	
	/**
	 * 
	 * @param tid
	 * @return
	 */
	Integer delete(int tId);
}
