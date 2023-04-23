package com.company.payroll.service;

import java.time.LocalDate;
import java.util.List;

import com.company.payroll.model.Manager;

public interface ManagerService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Manager> listManager();
	
	/** 
	 * 
	 * @return
	 * */
	Manager getInfoByUsername(String username);
	
	/** 
	 * 
	 * @return
	 * */
	Manager getInfoById(int mid);
	
	/** 
	 * 
	 * @return
	 * */
	Manager getInfoBySapId(int msapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer countManager();
	
	/** 
	 * 
	 * @return
	 * */
	Integer countAvailableManager();
	
	/** 
	 * 
	 * @return
	 * */
	Integer getManagerSapId();
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertManager(Manager manager);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateInfoById(Manager manager);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateInfoBySapId(Manager manager);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateResignDate(LocalDate dateresign, int msapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteManager(int mid);
}
