package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.StaffBankingInfo;
import com.github.pagehelper.PageInfo;

public interface StaffBankingInfoService {

	/**
	 * Method of retrieve staff banking info object based of banking info id.
	 * @param bId banking info id
	 * @return {@link StaffBankingInfo} object
	 */
	Optional<StaffBankingInfo> findById(Integer bId);
	
	/**
	 * Method of listing staff banking info
	 * @param page page number
	 * @param offset page data limit
	 * @return List of StaffBankingInfo
	 */
	PageInfo<StaffBankingInfo> list(int page, int offset);

	/**
	 * Method of updating staff banking info based on banking info id
	 * @param staffBankingInfo {@link StaffBankingInfo} object
	 * @return StaffBankingInfo object after updated.
	 */
	StaffBankingInfo update(StaffBankingInfo staffBankingInfo);

}
