package com.company.payroll.service;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffPromotion;
import com.company.payroll.model.StaffResignation;
import com.company.payroll.model.StaffTraining;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface StaffMiscellaneousService {
	
	/**
	 * Method of deleting promotion information from back end server.
	 * @param pId promotion id
	 * @return Deleted row
	 */
	Integer deletePromotion(Integer pId);
	
	/**
	 * Method of delete resignation information from back end server.
	 * @param resignId resign id
	 * @return Deleted row
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteResignation(Integer resignId);
	
	/**
	 * Method of deleting training information from back end server.
	 * @param tId training id
	 * @return Deleted row
	 */
	Integer deleteTraining(Integer tId);
	
	/**
	 * Method of retrieve StaffPromotion object based on promotion id
	 * @param pId promotion id
	 * @return {@link StaffPromotion} object
	 */
	Optional<StaffPromotion> findPromotionById(Integer pId);
	
	/**
	 * Method of retrieve staff resignation object based on resign id
	 * @param resignId resign id
	 * @return {@link StaffResignation} object
	 */
	Optional<StaffResignation> findResignationById(Integer resignId);

	/**
	 * Method of retrieve staff training object based on training id
	 * @param tId training id
	 * @return {@link StaffTraining} object
	 */
	Optional<StaffTraining> findTrainingById(Integer tId);
	
	/**
	 * @deprecated Please use {@link #listTrainingByStaffId(Integer)} instead.
	 * @param eId
	 * @return
	 */
	Optional<List<StaffTraining>> findTrainingByEId(int eId);
	
	/**
	 * @deprecated Please use {@link #listTrainingByStaffId(Integer)} instead.
	 * @param mId
	 * @return
	 */
	Optional<List<StaffTraining>> findTrainingByMId(int mId);

	/**
	 * Method of listing staff training based on staff id
	 * @param staffId staff id
	 * @return List of staff training
	 */
	List<StaffTraining> listTrainingByStaffId(Integer staffId);
	
	/**
	 * Method of inserting staff promotion information to back end server.
	 * @param staffPromotion {@link StaffPromotion} object
	 * @return {@link StaffPromotion} object after inserted.
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
    StaffPromotion insertPromotion(StaffPromotion staffPromotion);
	
	/**
	 * @deprecated Please use {@link #insertResignation(MultipartFile, StaffResignation)} instead.
	 * @param resign
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	StaffResignation insertResignation(StaffResignation resign);

	/**
	 * Method of insert resignation information to back end server.
	 * @param attachment attachment file for resignation info
	 * @param staffResignation {@link StaffResignation} object
	 * @return {@link StaffResignation} object after inserted.
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	StaffResignation insertResignation(MultipartFile attachment, StaffResignation staffResignation);

	/**
	 * Method of insert training information to back end server.
	 * @param staffTraining {@link StaffTraining} object
	 * @return {@link StaffTraining} object after inserted.
	 */
	StaffTraining insertTraining(StaffTraining staffTraining);
	
	/**
	 * Method of listing promotion information.
	 * @param page page number
	 * @param offset page data limit
	 * @return List of staff promotion
	 */
	PageInfo<StaffPromotion> listPromotion(int page, int offset);
	
	/**
	 * @deprecated Please use {@link #listPromotionByStaffId(int, int, Integer)} instead
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<StaffPromotion> listPromotionByEId(int page, int offset, int eId);

	/**
	 * Method of listing promotion information based on staff id
	 * @param page page number
	 * @param offset page data limit
	 * @param staffId staff id
	 * @return List of staff promotion
	 */
	PageInfo<StaffPromotion> listPromotionByStaffId(int page, int offset, Integer staffId);

	/**
	 * Method of listing resignation information
	 * @param page page number
	 * @param offset page data offset
	 * @return List of staff resignation
	 */
	PageInfo<StaffResignation> listResignation(int page, int offset);
	
	/**
	 * Method of listing staff training information
	 * @param page page number
	 * @param offset page data offset
	 * @return List of staff training
	 */
	PageInfo<StaffTraining> listTraining(int page, int offset);
	
	/**
	 * Method of updating staff promotion information based on promotion id
	 * @param staffPromotion {@link StaffPromotion} object
	 * @return {@link StaffPromotion} object after updated
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
    StaffPromotion updatePromotion(StaffPromotion staffPromotion);
	
	/**
	 * Method of updating staff resignation information based on resignation id
	 * @param staffResignation {@link StaffResignation} object
	 * @return {@link StaffResignation} object after updated.
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	StaffResignation updateResignation(StaffResignation staffResignation);
	
	/**
	 * Method of updating staff training information based on training id
	 * @param staffTraining {@link StaffTraining} object
	 * @return {@link StaffTraining} object after updated.
	 */
	StaffTraining updateTraining(StaffTraining staffTraining);

	/**
	 * Method of downloading resignation attachment file
	 * @param resignId resign id
	 * @return File resource
	 */
	Resource downloadResignationAttachment(Integer resignId);
}