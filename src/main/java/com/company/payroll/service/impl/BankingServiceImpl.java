package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.BankingInfoMapper;
import com.company.payroll.model.BankingInfo;
import com.company.payroll.service.BankingService;

@Service
public class BankingServiceImpl implements BankingService {

	private BankingInfoMapper bankingInfoMapper;
	
	public BankingServiceImpl(BankingInfoMapper bankingInfoMapper) {
		this.bankingInfoMapper = bankingInfoMapper;
	}
	
	@Override
	public List<BankingInfo> getList() {
		return bankingInfoMapper.selectList();
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
