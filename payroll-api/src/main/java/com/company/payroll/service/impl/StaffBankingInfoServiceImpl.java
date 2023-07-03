package com.company.payroll.service.impl;

import java.util.Optional;

import com.company.payroll.model.StaffBankingInfo;
import com.company.payroll.service.StaffBankingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.StaffBankingInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffBankingInfoServiceImpl implements StaffBankingInfoService {

	@Autowired
	private StaffBankingInfoMapper staffBankingInfoMapper;
	
	@Override
	public Optional<StaffBankingInfo> findById(Integer bId) {
		return Optional.ofNullable(staffBankingInfoMapper.selectByPrimaryKey(bId));
	}

	@Override
	public PageInfo<StaffBankingInfo> list(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffBankingInfo>(staffBankingInfoMapper.selectList());
	}

	@Override
	public StaffBankingInfo update(StaffBankingInfo bankingInfo) {
		staffBankingInfoMapper.updateByPrimaryKey(bankingInfo);
		return bankingInfo;
	}
}
