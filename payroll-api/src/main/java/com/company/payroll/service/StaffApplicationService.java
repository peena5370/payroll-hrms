package com.company.payroll.service;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffLeave;
import com.company.payroll.model.StaffLoan;
import com.github.pagehelper.PageInfo;

import javax.swing.text.html.Option;

public interface StaffApplicationService {

	/**
	 * Method of deleting leave detail based on leave id.
	 * @param lId leave id
	 * @return Deleted row
	 */
	Integer deleteLeave(Integer lId);
	
	/**
	 * Method of deleting loan detail based on loan id.
	 * @param loanId loan id
	 * @return Deleted row
	 */
	Integer deleteLoan(Integer loanId);
	
	/**
	 * @deprecated Renamed method to {@link #findLeaveByStaffId(Integer)}
	 * @param eid
	 * @return
	 */
	Optional<List<StaffLeave>> findLeaveByEId(int eid);

	/**
	 * Method of listing leave details by staff id
	 * @param staffId staff id
	 * @return List of staff leave
	 */
	Optional<List<StaffLeave>> findLeaveByStaffId(Integer staffId);

	/**
	 * Method of retrieve staff leave object based of leave id
	 * @param lId Leave id
	 * @return {@link StaffLeave} object
	 */
	Optional<StaffLeave> findLeaveById(Integer lId);
	
	/**
	 * @deprecated Renamed method to {@link #findLoanByStaffId(Integer)}
	 * @param eid
	 * @return
	 */
	Optional<List<StaffLoan>> findLoanByEId(int eid);

	/**
	 * Method of listing loan details based on staff id
	 * @param staffId staff id
	 * @return List of staff loan
	 */
	Optional<List<StaffLoan>> findLoanByStaffId(Integer staffId);

	/**
	 * Method of retrieve staff loan object by loan id.
	 * @param loanId loan id
	 * @return {@link StaffLoan} object
	 */
	Optional<StaffLoan> findLoanById(Integer loanId);
	
	/**
	 * Method of inserting staff leave into back end server.
	 * @param staffLeave {@link StaffLeave} object
	 * @return {@link StaffLeave} object after inserted.
	 */
	StaffLeave insertLeave(StaffLeave staffLeave);
	
	/**
	 * Method of inserting staff loan into back end server.
	 * @param staffLoan {@link StaffLoan} object
	 * @return {@link StaffLoan} object after inserted
	 */
	StaffLoan insertLoan(StaffLoan staffLoan);
	
	/**
	 * Method of listing staff leave.
	 * @param page page number
	 * @param offset page data limit
	 * @return List of staff leave
	 */
	PageInfo<StaffLeave> listLeave(int page, int offset);
	
	/**
	 * Method of listing staff loan.
	 * @param page page number
	 * @param offset page data limit
	 * @return List of staff loan
	 */
	PageInfo<StaffLoan> listLoan(int page, int offset);
	
	/**
	 * Method of update staff leave.
	 * @param staffLeave {@link StaffLeave} object
	 * @return {@link StaffLeave} after updated.
	 */
	StaffLeave updateLeave(StaffLeave staffLeave);
	
	/**
	 * Method of update staff loan.
	 * @param staffLoan {@link StaffLoan} object
	 * @return {@link StaffLoan} after updated.
	 */
	StaffLoan updateLoan(StaffLoan staffLoan);
}
