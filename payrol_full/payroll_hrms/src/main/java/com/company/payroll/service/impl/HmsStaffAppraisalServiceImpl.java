package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.HmsStaffAppraisalMapper;
import com.company.payroll.model.HmsStaffAppraisal;
import com.company.payroll.service.HmsStaffAppraisalService;
import com.company.payroll.util.SnowFlakeIdGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HmsStaffAppraisalServiceImpl implements HmsStaffAppraisalService {
	
	@Autowired
	private SnowFlakeIdGenerator idGenerator;

	@Autowired
	private HmsStaffAppraisalMapper hmsStaffAppraisalMapper;
	
	@Override
	public PageInfo<HmsStaffAppraisal> getAllStaffAppraisal(int page, int offset) {
		PageHelper.startPage(page, offset);
		// TODO Auto-generated method stub
		return new PageInfo<HmsStaffAppraisal>(null);
	}

	@Override
	public Optional<HmsStaffAppraisal> getStaffAppraisalById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	@Override
	public Integer insertStaffAppraisal(HmsStaffAppraisal staffAppraisal) {
		staffAppraisal.setId(idGenerator.nextId());
		
		return hmsStaffAppraisalMapper.insertSelective(staffAppraisal);
	}

	@Override
	public Integer updateStaffAppraisal(HmsStaffAppraisal staffAppraisal) {
		return hmsStaffAppraisalMapper.updateByPrimaryKey(staffAppraisal);
	}

	@Override
	public Integer deleteStaffAppraisalById(Long id) {
		return hmsStaffAppraisalMapper.deleteByPrimaryKey(id);
	}

}
