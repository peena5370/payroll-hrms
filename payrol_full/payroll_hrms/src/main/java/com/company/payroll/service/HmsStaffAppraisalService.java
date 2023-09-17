package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.HmsStaffAppraisal;
import com.github.pagehelper.PageInfo;

public interface HmsStaffAppraisalService {
	
	PageInfo<HmsStaffAppraisal> getAllStaffAppraisal(int page, int offset);
	
	Optional<HmsStaffAppraisal> getStaffAppraisalById(Long id);
	
	Integer insertStaffAppraisal(HmsStaffAppraisal staffAppraisal);
	
	Integer updateStaffAppraisal(HmsStaffAppraisal staffAppraisal);
	
	Integer deleteStaffAppraisalById(Long id);
}
