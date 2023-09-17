package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.HmsStaffLearningDetailMapper;
import com.company.payroll.mapper.HmsStaffLearningMapper;
import com.company.payroll.model.HmsStaffLearning;
import com.company.payroll.model.HmsStaffLearningDetail;
import com.company.payroll.service.HmsStaffLearningService;
import com.github.pagehelper.PageInfo;

@Service
public class HmsStaffLearningServiceImpl implements HmsStaffLearningService {

	@Autowired
	private HmsStaffLearningMapper hmsStaffLearningMapper;
	
	@Autowired
	private HmsStaffLearningDetailMapper hmsStaffLearningDetailMapper;
	
	@Override
	public PageInfo<HmsStaffLearning> getAllStaffLearning(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsStaffLearning> getStaffLearningById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertStaffLearning(HmsStaffLearning hmsStaffLearning) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStaffLearning(HmsStaffLearning hmsStaffLearning) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStaffLearningById(Long id) {
		return hmsStaffLearningMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<HmsStaffLearningDetail> getAllStaffLearningDetail(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsStaffLearningDetail> getStaffLearningDetailById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertStaffLearningDetail(HmsStaffLearningDetail hmsStaffLearningDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStaffLearningDetail(HmsStaffLearningDetail hmsStaffLearningDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteStaffLearningDetailById(Long id) {
		return hmsStaffLearningDetailMapper.deleteByPrimaryKey(id);
	}

}
