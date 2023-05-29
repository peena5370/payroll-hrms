package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Leave;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @deprecated replaced with {@link #StaffApplicationService} class
 */
@Deprecated
public interface LeaveService {
	
	/**
	 * 
	 * @return
	 */
	PageInfo<Leave> getListByPage(int page, int offset);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	List<Leave> getListByEId(int eid);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Leave getById(int lid);

	/**
	 * 
	 * @param leave
	 * @return
	 */
	Integer insert(Leave leave);
	
	/**
	 * 
	 * @param leave
	 * @return
	 */
	Integer update(Leave leave);
	
	/**
	 * 
	 * @param lid
	 * @return
	 */
	Integer delete(int lid);
}
