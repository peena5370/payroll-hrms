package com.company.payroll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.payroll.model.Promotion;
import com.company.payroll.model.Resignation;
import com.company.payroll.model.Training;
import com.github.pagehelper.PageInfo;

public interface StaffMiscellaneousService {
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	Integer deletePromotion(int pid);
	
	/**
	 * 
	 * @param resignid
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteResignation(int resignid);
	
	/**
	 * 
	 * @param tId
	 * @return
	 */
	Integer deleteTraining(int tId);
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	Optional<Promotion> findPromotionById(int pid);
	
	/**
	 * 
	 * @param resignid
	 * @return
	 */
	Optional<Resignation> findResignationById(int resignid);
	
	/**
	 * 
	 * @param eId
	 * @return
	 */
	Optional<List<Training>> findTrainingByEId(int eId);
	
	/**
	 * 
	 * @param mId
	 * @return
	 */
	Optional<List<Training>> findTrainingByMId(int mId);
	
	/**
	 * 
	 * @param tId
	 * @return
	 */
	Optional<Training> findTrainingById(int tId);
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Promotion insertPromotion(Promotion promotion);
	
	/**
	 * 
	 * @param resign
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Resignation insertResignation(Resignation resign);
	
	/**
	 * 
	 * @param training
	 * @return
	 */
	Training insertTraining(Training training);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Promotion> listPromotion(int page, int offset);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Promotion> listPromotionByEId(int page, int offset, int eId);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Resignation> listResignation(int page, int offset);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Training> listTraining(int page, int offset);
	
	/**
	 * 
	 * @param promotion
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Promotion updatePromotion(Promotion promotion);
	
	/**
	 * 
	 * @param resign
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Resignation updateResignation(Resignation resign);
	
	/**
	 * 
	 * @param training
	 * @return
	 */
	Training updateTraining(Training training);
}
