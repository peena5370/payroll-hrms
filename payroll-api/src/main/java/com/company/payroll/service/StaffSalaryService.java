package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.StaffSalary;
import com.github.pagehelper.PageInfo;

public interface StaffSalaryService {
	
	/**
	 * Get StaffSalary object by salary id
	 * @param sId salary id
	 * @return {@link StaffSalary} object
	 */
	Optional<StaffSalary> findById(Integer sId);
	
	/**
	 * Method of listing StaffSalary
	 * @param page page number
	 * @param offset page data limit
	 * @return List of StaffSalary
	 */
	PageInfo<StaffSalary> list(int page, int offset);
	
	/**
	 * Method of update StaffSalary
	 * @param staffSalary {@link StaffSalary} object
	 * @return Updated {@link StaffSalary} object
	 */
	StaffSalary update(StaffSalary staffSalary);
}
