package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Leave;

public interface LeaveService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Leave> listLeave();
	
	/** 
	 * 
	 * @return
	 * */
	List<Leave> listLeaveBySapId(int esapid);

	/** 
	 * 
	 * @return
	 * */
	Integer insertLeave(Leave leave);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateLeave(Leave leave);
}
