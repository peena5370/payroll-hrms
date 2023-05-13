package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.BankingInfoMapper;
import com.company.payroll.model.BankingInfo;
import com.company.payroll.service.BankingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BankingServiceImpl implements BankingService {

	@Autowired
	private BankingInfoMapper bankingInfoMapper;
	
	@Override
	public PageInfo<BankingInfo> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<BankingInfo> list = bankingInfoMapper.selectList();
		return new PageInfo<BankingInfo>(list);
	}

	@Override
	public BankingInfo getById(int bid) {
		return bankingInfoMapper.selectByPrimaryKey(bid);
	}

	@Override
	public Integer insert(BankingInfo bankingInfo) {
		return bankingInfoMapper.insertSelective(bankingInfo);
	}

	@Override
	public Integer updateById(BankingInfo bankingInfo) {
		return bankingInfoMapper.updateByPrimaryKey(bankingInfo);
	}

	@Override
	public Integer delete(int bid) {
		return bankingInfoMapper.deleteByPrimaryKey(bid);
	}
}
