package com.company.payroll.service;

import java.util.Optional;

import com.company.payroll.model.HmsStaffLearning;
import com.company.payroll.model.HmsStaffLearningDetail;
import com.github.pagehelper.PageInfo;

public interface HmsStaffLearningService {
	
	PageInfo<HmsStaffLearning> getAllStaffLearning(int page, int offset);
	
	Optional<HmsStaffLearning> getStaffLearningById(Long id);

	Integer insertStaffLearning(HmsStaffLearning hmsStaffLearning);
	
	Integer updateStaffLearning(HmsStaffLearning hmsStaffLearning);
	
	Integer deleteStaffLearningById(Long id);
	
	PageInfo<HmsStaffLearningDetail> getAllStaffLearningDetail(int page, int offset);
	
	Optional<HmsStaffLearningDetail> getStaffLearningDetailById(Long id);
	
	Integer insertStaffLearningDetail(HmsStaffLearningDetail hmsStaffLearningDetail);
	
	Integer updateStaffLearningDetail(HmsStaffLearningDetail hmsStaffLearningDetail);
	
	Integer deleteStaffLearningDetailById(Long id);
}
