package com.company.payroll.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.payroll.model.Employee;
import com.company.payroll.model.Manager;
import com.github.pagehelper.PageInfo;

public interface StaffDetailsService {
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteEmployee(int eid);
	
	/**
	 * 
	 * @param mid
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Integer deleteManager(int mid);
	
	/**
	 * 
	 * @param eid
	 * @return
	 */
	Optional<Employee> findEmployeeById(int eid);
	
	/**
	 * 
	 * @param mid
	 * @return
	 */
	Optional<Manager> findManagerById(int mid);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Employee> listEmployee(int page, int offset);
	
	/**
	 * 
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<Manager> listManager(int page, int offset);
	
	/**
	 * 
	 * @param employee
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Employee registerEmployee(Employee employee);
	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRES_NEW)
	Manager registerManager(Manager manager);
	
	/**
	 * 
	 * @param employee
	 * @return
	 */
	Employee updateEmployee(Employee employee);
	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	Manager updateManager(Manager manager);
}
