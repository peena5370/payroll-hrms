package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.HmsStaffDetailMapper;
import com.company.payroll.mapper.HmsStaffJobTitleMapper;
import com.company.payroll.model.HmsStaffJobTitle;
import com.company.payroll.service.HmsStaffDetailService;
import com.company.payroll.util.SnowFlakeIdGenerator;

@Service
public class HsmStaffDetailServiceImpl implements HmsStaffDetailService {
	
	@Autowired
	private SnowFlakeIdGenerator idGenerator;
	
	@Autowired
	private HmsStaffJobTitleMapper jobTitleMapper;

	@Override
	public int createJobTitle(HmsStaffJobTitle jobTitle) {
		jobTitle.setId(idGenerator.nextId());
		System.out.println("service: " + Long.toBinaryString(jobTitle.getId()) + "\tname: " + jobTitle.getName() + "\tdesc: " + jobTitle.getDescription());
		return jobTitleMapper.insert(jobTitle);
	}

	@Override
	public HmsStaffJobTitle getJobTitle(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateJobTitle(HmsStaffJobTitle jobTitle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteJobTitle(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HmsStaffJobTitle> getAllJobTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
