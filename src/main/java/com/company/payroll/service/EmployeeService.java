package com.company.payroll.service;

import java.time.LocalDate;
import java.util.List;

import com.company.payroll.model.Employee;

public interface EmployeeService {
	
	/** 
	 * 
	 * @return
	 * */
	List<Employee> listEmployee();
	
	/** 
	 * 
	 * @return
	 * */
	Employee getEmployeeById(int eid);
	
	/** 
	 * 
	 * @return
	 * */
	Employee getEmployeeBySapId(int esapid);
	
	/** 
	 * 
	 * @return
	 * */
	Employee getEmployeePasswordBySapId(int esapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer countEmployee();
	
	/** 
	 * 
	 * @return
	 * */
	Integer countAvailableEmployee();
	
	/** 
	 * 
	 * @return
	 * */
	Integer getEmployeeSapId();
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertEmployee(Employee emp);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateInfoByManager(Employee emp);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateInfoByEmployee(Employee emp);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updatePasswordBySapId(Employee emp);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateResignDateBySapId(int sapid, LocalDate dateresign);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteEmployee(int eid);
}
