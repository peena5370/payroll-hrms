package com.company.payroll.service;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffPayroll;
import com.company.payroll.model.PayrollManager;
import com.github.pagehelper.PageInfo;

public interface StaffPayrollService {
	/**
	 * @deprecated Please use {@link #deleteStaffPayroll(Integer)} instead.
	 * @param prid
	 * @return
	 */
	Integer deletePayrollEmployee(int prid);
	
	/**
	 * @deprecated Please use {@link #deleteStaffPayroll(Integer)} instead.
	 * @param prMgrId
	 * @return
	 */
	Integer deletePayrollManager(int prMgrId);

	/**
	 * Method of deleting staff payroll based on payroll id
	 * @param prId payroll id
	 * @return Deleted row
	 */
	Integer deleteStaffPayroll(Integer prId);
	
	/**
	 * @deprecated Please use {@link #findStaffPayrollById(Integer)} instead.
	 * @param prid
	 * @return
	 */
	Optional<StaffPayroll> findPayrollEmployeeById(int prid);
	
	/**
	 * @deprecated Please use {@link #findStaffPayrollById(Integer)} instead.
	 * @param prMgrId
	 * @return
	 */
	Optional<PayrollManager> findPayrollManagerById(int prMgrId);

	/**
	 * Method of retrieve staff payroll based on payroll id
	 * @param prId payroll id
	 * @return {@link StaffPayroll} object
	 */
	Optional<StaffPayroll> findStaffPayrollById(Integer prId);

	/**
	 * @deprecated Please use {@link #findStaffPayrollByStaffId(Integer)} instead.
	 * @param eid
	 * @return
	 */
	Optional<List<StaffPayroll>> findPayrollEmployeeByEId(int eid);
	
	/**
	 * @deprecated Please use {@link #findStaffPayrollByStaffId(Integer)} instead.
	 * @param mid
	 * @return
	 */
	Optional<List<PayrollManager>> findPayrollManagerByMId(int mid);

	/**
	 * Method of listing staff payroll based on staff id
	 * @param staffId staff id
	 * @return List of staff payroll
	 */
	List<StaffPayroll> findStaffPayrollByStaffId(Integer staffId);

	/**
	 * @deprecated Please use {@link #insertStaffPayroll(StaffPayroll)} instead.
	 * @param payroll
	 * @return
	 */
	StaffPayroll insertPayrollEmployee(StaffPayroll payroll);
	
	/**
	 * @deprecated Please use {@link #insertStaffPayroll(StaffPayroll)} instead.
	 * @param payrollManager
	 * @return
	 */
	PayrollManager insertPayrollManager(PayrollManager payrollManager);

	/**
	 * Method of inserting staff payroll information to back end server.
	 * @param staffPayroll {@link StaffPayroll} object
	 * @return {@link StaffPayroll} object after inserted.
	 */
	StaffPayroll insertStaffPayroll(StaffPayroll staffPayroll);

	/**
	 * @deprecated Please use {@link #listStaffPayroll(int, int)} instead.
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<StaffPayroll> listPayrollEmployee(int page, int offset);
	
	/**
	 * @deprecated Please use {@link #listStaffPayroll(int, int)} instead.
	 * @param page
	 * @param offset
	 * @return
	 */
	PageInfo<PayrollManager> listPayrollManager(int page, int offset);

	/**
	 * Method of listing staff payroll
	 * @param page page number
	 * @param offset page data limit
	 * @return List of staff payroll
	 */
	PageInfo<StaffPayroll> listStaffPayroll(int page, int offset);

	/**
	 * @deprecated Please use {@link #updateStaffPayroll(StaffPayroll)} instead.
	 * @param payroll
	 * @return
	 */
	StaffPayroll updatePayrollEmployee(StaffPayroll payroll);
	
	/**
	 * @deprecated Please use {@link #updateStaffPayroll(StaffPayroll)} instead.
	 * @param payrollManager
	 * @return
	 */
	PayrollManager updatePayrollManager(PayrollManager payrollManager);

	/**
	 * Method of updating staff payroll based on payroll id
	 * @param staffPayroll {@link StaffPayroll} object
	 * @return {@link StaffPayroll} object after updated
	 */
	StaffPayroll updateStaffPayroll(StaffPayroll staffPayroll);
}
