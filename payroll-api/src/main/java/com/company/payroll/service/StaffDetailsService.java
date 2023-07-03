package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.StaffDetails;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.payroll.model.Manager;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface StaffDetailsService {
	
	/**
	 * @deprecated WIll be replaced with {@link #countActiveStaff(Integer)}
	 * @param deptno
	 * @return
	 */
	Integer countActiveEmployee(int deptno);
	
	/**
	 * @deprecated Will be replaced with {@link #countActiveStaff(Integer)}
	 * @param deptno
	 * @return
	 */
	Integer countActiveManager(int deptno);

	/**
	 * Method of count active staff based on department.<br>
	 * <b>To be implemented.</b>
	 * @param deptNo department number
	 * @return
	 */
	Integer countActiveStaff(Integer deptNo);
	
	/**
	 * @deprecated Please use {@link #deleteStaffDetails(Integer)} instead.
	 * @param eid
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteEmployee(int eid);
	
	/**
	 * @deprecated Please use {@link #deleteStaffDetails(Integer)} instead.
	 * @param mid
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteManager(int mid);

	/**
	 * Method of deleting staff detail based on staff id.
	 * @param staffId staff id
	 * @return Deleted row
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteStaffDetails(Integer staffId);
	
	/**
	 * @deprecated Please use {@link #findByStaffId(Integer)} instead.
	 * @param eid
	 * @return
	 */
	Optional<StaffDetails> findEmployeeById(int eid);
	
	/**
	 * @deprecated Please use {@link #findByStaffId(Integer)} instead.
	 * @param mid
	 * @return
	 */
	Optional<Manager> findManagerById(int mid);

	/**
	 * Method of retrieve staff details based on staff id
	 * @param staffId staff id
	 * @return {@link StaffDetails} object
	 */
	Optional<StaffDetails> findByStaffId(Integer staffId);
	
	/**
	 * @deprecated Please use {@link #listStaffDetails(int, int)} instead.
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<StaffDetails> listEmployee(int page, int offset);
	
	/**
	 * @deprecated Please use {@link #listStaffDetails(int, int)} instead.
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Manager> listManager(int page, int offset);

	/**
	 * Method of listing staff details from back end server.
	 * @param page page number
	 * @param offset page data limit
	 * @return List of staff details
	 */
	PageInfo<StaffDetails> listStaffDetails(int page, int offset);
	
	/**
	 * @deprecated Please use {@link #addStaffDetails(MultipartFile, StaffDetails)} instead.
	 * @param staffDetails
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
    StaffDetails registerEmployee(StaffDetails staffDetails);

	/**
	 * @deprecated Please use {@link #addStaffDetails(MultipartFile, StaffDetails)} instead.
	 * @param manager
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Manager registerManager(Manager manager);

	/**
	 * Method of insert staff details into back end server
	 * @param staffImage image file of staff
	 * @param staffDetails {@link StaffDetails} object
	 * @return {@link StaffDetails} object after inserted.
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	StaffDetails addStaffDetails(MultipartFile staffImage, StaffDetails staffDetails);
	
	/**
	 * @deprecated Please use {@link #updateStaffDetails(StaffDetails)} instead.
	 * @param staffDetails
	 * @return
	 */
	StaffDetails updateEmployee(StaffDetails staffDetails);
	
	/**
	 * @deprecated Please use {@link #updateStaffDetails(StaffDetails)} instead.
	 * @param manager
	 * @return
	 */
	Manager updateManager(Manager manager);

	/**
	 * Method of updating staff details based on staff id
	 * @param staffDetails {@link StaffDetails} object
	 * @return {@link StaffDetails} object after updated
	 */
	StaffDetails updateStaffDetails(StaffDetails staffDetails);

	/**
	 * Method of retrieve staff image from back end server.
	 * @param staffId staff id
	 * @return Image resource
	 */
	Resource loadStaffImage(Integer staffId);
}
